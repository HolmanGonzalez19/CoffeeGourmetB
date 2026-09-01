package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.request.CloseCashRegisterRequest;
import com.cgb.coffeegourmetb.dto.request.OpenCashRegisterRequest;
import com.cgb.coffeegourmetb.dto.response.CashRegisterResponse;
import com.cgb.coffeegourmetb.dto.response.CashRegisterStatusResponse;
import com.cgb.coffeegourmetb.entity.CashRegister;
import com.cgb.coffeegourmetb.entity.User;
import com.cgb.coffeegourmetb.enums.CashMovementType;
import com.cgb.coffeegourmetb.enums.CashRegisterStatus;
import com.cgb.coffeegourmetb.exception.BusinessException;
import com.cgb.coffeegourmetb.exception.ResourceNotFoundException;
import com.cgb.coffeegourmetb.mapper.CashRegisterMapper;
import com.cgb.coffeegourmetb.repository.CashMovementRepository;
import com.cgb.coffeegourmetb.repository.CashRegisterRepository;
import com.cgb.coffeegourmetb.repository.SaleRepository;
import com.cgb.coffeegourmetb.repository.UserRepository;
import com.cgb.coffeegourmetb.service.interfaces.CashRegisterService;
import com.cgb.coffeegourmetb.util.constants.ApiMessages;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CashRegisterServiceImpl
        implements CashRegisterService {

    private final CashRegisterRepository repository;
    private final UserRepository userRepository;
    private final CashRegisterMapper mapper;
    private final SaleRepository saleRepository;
    private final CashMovementRepository cashMovementRepository;

    public CashRegisterServiceImpl(
            CashRegisterRepository repository,
            UserRepository userRepository,
            CashRegisterMapper mapper,
            SaleRepository saleRepository,
            CashMovementRepository cashMovementRepository) {

        this.repository = repository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.saleRepository = saleRepository;
        this.cashMovementRepository = cashMovementRepository;
    }

    @Override
    public CashRegisterResponse open(
            OpenCashRegisterRequest request) {

        if (repository.existsByEstado(
                CashRegisterStatus.ABIERTA)) {

            throw new BusinessException(
                    ApiMessages.CASH_REGISTER_ALREADY_OPEN);
        }

        User usuario = obtenerUsuarioAutenticado();

        CashRegister caja =
                new CashRegister();

        caja.setUsuarioApertura(usuario);
        caja.setFechaApertura(LocalDateTime.now());
        caja.setMontoInicial(request.getMontoInicial());
        caja.setEstado(CashRegisterStatus.ABIERTA);
        caja.setEfectivoEsperado(request.getMontoInicial());

        repository.save(caja);

        return construirRespuesta(caja);
    }

    @Override
    public CashRegisterResponse close(
            Long id,
            CloseCashRegisterRequest request) {

        CashRegister caja =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        ApiMessages.CASH_REGISTER_NOT_FOUND));

        if (caja.getEstado() !=
                CashRegisterStatus.ABIERTA) {

            throw new BusinessException(
                    ApiMessages.CASH_REGISTER_ALREADY_CLOSED);
        }

        User usuario = obtenerUsuarioAutenticado();

        BigDecimal ventasEfectivo =
                saleRepository.sumTotalByCajaAndPaymentMethod(
                        caja,
                        "EFECTIVO");

        BigDecimal ingresos =
                cashMovementRepository
                        .sumMontoByCajaAndTipoMovimiento(
                                caja,
                                CashMovementType.INGRESO);

        BigDecimal retiros =
                cashMovementRepository
                        .sumMontoByCajaAndTipoMovimiento(
                                caja,
                                CashMovementType.RETIRO);

        BigDecimal efectivoEsperado =
                caja.getMontoInicial()
                        .add(ventasEfectivo)
                        .add(ingresos)
                        .subtract(retiros);

        BigDecimal diferencia =
                request.getEfectivoContado()
                        .subtract(efectivoEsperado);

        caja.setUsuarioCierre(usuario);
        caja.setFechaCierre(LocalDateTime.now());
        caja.setEstado(CashRegisterStatus.CERRADA);
        caja.setEfectivoEsperado(
                efectivoEsperado);
        caja.setEfectivoContado(
                request.getEfectivoContado());
        caja.setDiferencia(diferencia);

        repository.save(caja);

        return construirRespuesta(caja);
    }

    @Override
    @Transactional(readOnly = true)
    public CashRegisterStatusResponse findOpen() {

        boolean cajaAbierta =
                repository.existsByEstado(
                        CashRegisterStatus.ABIERTA);

        return new CashRegisterStatusResponse(
                cajaAbierta
                        ? CashRegisterStatus.ABIERTA
                        : CashRegisterStatus.CERRADA
        );
    }

    @Override
    @Transactional(readOnly = true)
    public CashRegisterResponse findOpenAdministrative() {

        CashRegister caja =
                repository.findByEstado(
                                CashRegisterStatus.ABIERTA)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        ApiMessages.CASH_REGISTER_NOT_OPEN));

        return construirRespuesta(caja);
    }

    @Override
    @Transactional(readOnly = true)
    public CashRegisterResponse findById(
            Long id) {

        CashRegister caja =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        ApiMessages.CASH_REGISTER_NOT_FOUND));

        return construirRespuesta(caja);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CashRegisterResponse> findAll() {

        return repository
                .findAllByOrderByFechaAperturaDesc()
                .stream()
                .map(this::construirRespuesta)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CashRegisterResponse> findClosed() {

        return repository
                .findByEstadoOrderByFechaAperturaDesc(
                        CashRegisterStatus.CERRADA)
                .stream()
                .map(this::construirRespuesta)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CashRegister> obtenerCajaAbierta() {

        return repository.findByEstado(
                CashRegisterStatus.ABIERTA);
    }

    /**
     * Obtiene el usuario autenticado actualmente
     * a partir del contexto de Spring Security.
     */

    private User obtenerUsuarioAutenticado() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated()) {

            throw new BusinessException(
                    "Usuario no autenticado.");
        }

        String username = authentication.getName();

        return userRepository
                .findByUsuarioAndActivoTrue(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario autenticado no encontrado."));
    }

    private CashRegisterResponse construirRespuesta(
            CashRegister caja) {

        CashRegisterResponse response =
                mapper.toResponse(caja);


        // ============================================================
        // VENTAS
        // ============================================================

        BigDecimal ventasEfectivo =
                saleRepository.sumTotalByCajaAndPaymentMethod(
                        caja,
                        "EFECTIVO");

        BigDecimal ventasTransferencia =
                saleRepository.sumTotalByCajaAndPaymentMethod(
                        caja,
                        "TRANSFERENCIA");

        BigDecimal ventasTotales =
                saleRepository.sumTotalRegisteredByCaja(caja);


        // ============================================================
        // MOVIMIENTOS DE CAJA
        // ============================================================

        BigDecimal ingresos =
                cashMovementRepository
                        .sumMontoByCajaAndTipoMovimiento(
                                caja,
                                CashMovementType.INGRESO);

        BigDecimal retiros =
                cashMovementRepository
                        .sumMontoByCajaAndTipoMovimiento(
                                caja,
                                CashMovementType.RETIRO);


        // ============================================================
        // EFECTIVO ESPERADO
        // ============================================================

        BigDecimal efectivoEsperado =
                caja.getMontoInicial()
                        .add(ventasEfectivo)
                        .add(ingresos)
                        .subtract(retiros);


        // ============================================================
        // RESPUESTA
        // ============================================================

        response.setVentasEfectivo(
                ventasEfectivo);

        response.setVentasTransferencia(
                ventasTransferencia);

        response.setVentasTotales(
                ventasTotales);

        response.setEfectivoEsperado(
                efectivoEsperado);


        return response;
    }
}