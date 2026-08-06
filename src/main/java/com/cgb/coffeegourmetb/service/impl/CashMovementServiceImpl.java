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
import com.cgb.coffeegourmetb.util.constants.ApiMessages;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cgb.coffeegourmetb.enums.CashMovementType;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CashMovementServiceImpl
        implements CashMovementService {

    private final CashMovementRepository repository;
    private final CashRegisterRepository cashRegisterRepository;
    private final UserRepository userRepository;
    private final CashMovementMapper mapper;

    public CashMovementServiceImpl(
            CashMovementRepository repository,
            CashRegisterRepository cashRegisterRepository,
            UserRepository userRepository,
            CashMovementMapper mapper) {

        this.repository = repository;
        this.cashRegisterRepository = cashRegisterRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public CashMovementResponse create(
            CreateCashMovementRequest request) {

        CashRegister caja =
                cashRegisterRepository.findById(
                                request.getCajaId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        ApiMessages.CASH_REGISTER_NOT_FOUND));

        if (caja.getEstado() !=
                CashRegisterStatus.ABIERTA) {

            throw new BusinessException(
                    "La caja se encuentra cerrada.");
        }

        User usuario =
                userRepository.findById(
                                request.getUsuarioId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Usuario no encontrado."));

        CashMovement movimiento =
                new CashMovement();

        movimiento.setCaja(caja);
        movimiento.setUsuario(usuario);
        movimiento.setTipoMovimiento(
                CashMovementType.valueOf(
                        request.getTipoMovimiento().toUpperCase()
                )
        );
        movimiento.setMonto(
                request.getMonto());
        movimiento.setDescripcion(
                request.getDescripcion());
        movimiento.setFechaMovimiento(
                LocalDateTime.now());

        repository.save(movimiento);

        return mapper.toResponse(
                movimiento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CashMovementResponse> findByCashRegister(
            Long cashRegisterId) {

        return repository
                .findByCajaIdOrderByFechaMovimientoDesc(
                        cashRegisterId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

}