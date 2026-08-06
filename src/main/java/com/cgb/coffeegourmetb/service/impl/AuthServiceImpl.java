package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.request.LoginRequest;
import com.cgb.coffeegourmetb.dto.request.PinLoginRequest;
import com.cgb.coffeegourmetb.dto.response.AuthenticationResponse;
import com.cgb.coffeegourmetb.entity.User;
import com.cgb.coffeegourmetb.exception.BusinessException;
import com.cgb.coffeegourmetb.repository.UserRepository;
import com.cgb.coffeegourmetb.security.JwtService;
import com.cgb.coffeegourmetb.service.interfaces.AuthService;
import com.cgb.coffeegourmetb.service.interfaces.CredentialService;
import com.cgb.coffeegourmetb.service.interfaces.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private static final String PERMISSION_OPERAR_CAJA =
            "OPERAR_CAJA";

    private final UserRepository userRepository;
    private final CredentialService credentialService;
    private final PermissionService permissionService;
    private final JwtService jwtService;

    public AuthServiceImpl(
            UserRepository userRepository,
            CredentialService credentialService,
            PermissionService permissionService,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.credentialService = credentialService;
        this.permissionService = permissionService;
        this.jwtService = jwtService;
    }

    @Override
    public AuthenticationResponse login(
            LoginRequest request) {

        User user = userRepository
                .findByUsuarioAndActivoTrue(
                        request.getUsuario())
                .orElseThrow(() ->
                        new BusinessException(
                                "Usuario o contraseña incorrectos."));

        if (!credentialService.matches(
                request.getPassword(),
                user.getPasswordHash())) {

            throw new BusinessException(
                    "Usuario o contraseña incorrectos.");
        }

        return buildAuthenticationResponse(user);
    }

    @Override
    public AuthenticationResponse loginWithPin(
            PinLoginRequest request) {

        User user = userRepository
                .findByUsuarioAndActivoTrue(
                        request.getUsuario())
                .orElseThrow(() ->
                        new BusinessException(
                                "Usuario o PIN incorrectos."));

        if (user.getPinHash() == null ||
                user.getPinHash().isBlank()) {

            throw new BusinessException(
                    "El usuario no tiene un PIN configurado.");
        }

        if (!credentialService.matches(
                request.getPin(),
                user.getPinHash())) {

            throw new BusinessException(
                    "Usuario o PIN incorrectos.");
        }

        boolean puedeOperarCaja =
                permissionService.userHasPermission(
                        user.getId(),
                        PERMISSION_OPERAR_CAJA);

        if (!puedeOperarCaja) {

            throw new BusinessException(
                    "El usuario no tiene permiso para operar caja.");
        }

        return buildAuthenticationResponse(user);
    }

    private AuthenticationResponse buildAuthenticationResponse(
            User user) {

        String token =
                jwtService.generateToken(user);

        Set<String> permisos =
                permissionService.getUserPermissions(
                        user.getId());

        AuthenticationResponse response =
                new AuthenticationResponse();

        response.setToken(token);

        response.setUsuarioId(
                user.getId());

        response.setNombre(
                user.getNombre());

        response.setUsuario(
                user.getUsuario());

        response.setRolId(
                user.getRole().getId());

        response.setRolNombre(
                user.getRole().getNombre());

        response.setPermisos(
                permisos);

        return response;
    }
}