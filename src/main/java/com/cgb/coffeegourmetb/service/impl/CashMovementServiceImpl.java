package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.request.CreateCashMovementRequest;
import com.cgb.coffeegourmetb.dto.response.CashMovementResponse;
import com.cgb.coffeegourmetb.entity.CashMovement;
import com.cgb.coffeegourmetb.entity.CashRegister;
import com.cgb.coffeegourmetb.entity.User;
import com.cgb.coffeegourmetb.enums.CashRegisterStatus;
import com.cgb.coffeegourmetb.exception.BusinessException;
import com.cgb.coffeegourmetb.exception.ResourceNotFoundException;
import com.cgb.coffeegourmetb.mapper.CashMovementMapper;
import com.cgb.coffeegourmetb.repository.CashMovementRepository;
import com.cgb.coffeegourmetb.repository.CashRegisterRepository;
import com.cgb.coffeegourmetb.repository.UserRepository;
import com.cgb.coffeegourmetb.service.interfaces.CashMovementService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CashMovementServiceImpl
        implements CashMovementService {

    private final CashMovementRepository cashMovementRepository;
    private final CashRegisterRepository cashRegisterRepository;
    private final UserRepository userRepository;
    private final CashMovementMapper mapper;

    public CashMovementServiceImpl(
            CashMovementRepository cashMovementRepository,
            CashRegisterRepository cashRegisterRepository,
            UserRepository userRepository,
            CashMovementMapper mapper) {

        this.cashMovementRepository = cashMovementRepository;
        this.cashRegisterRepository = cashRegisterRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public CashMovementResponse create(
            CreateCashMovementRequest request) {

        CashRegister caja =
                cashRegisterRepository
                        .findByEstado(
                                CashRegisterStatus.ABIERTA)
                        .orElseThrow(() ->
                                new BusinessException(
                                        "No existe una caja abierta."
                                ));

        User usuario =
                obtenerUsuarioAutenticado();

        CashMovement movimiento =
                new CashMovement();

        movimiento.setCaja(caja);
        movimiento.setUsuario(usuario);
        movimiento.setTipoMovimiento(
                request.getTipoMovimiento());
        movimiento.setMonto(
                request.getMonto());
        movimiento.setDescripcion(
                request.getDescripcion());
        movimiento.setFechaMovimiento(
                LocalDateTime.now());

        CashMovement guardado =
                cashMovementRepository.save(movimiento);

        return mapper.toResponse(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CashMovementResponse> findByCaja(
            Long cajaId) {

        CashRegister caja =
                cashRegisterRepository
                        .findById(cajaId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "No existe la caja con id: "
                                                + cajaId
                                ));

        return cashMovementRepository
                .findByCajaOrderByFechaMovimientoDesc(caja)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

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

        String username =
                authentication.getName();

        return userRepository
                .findByUsuarioAndActivoTrue(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario autenticado no encontrado."
                        ));
    }
}