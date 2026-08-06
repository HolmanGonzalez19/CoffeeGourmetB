package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.request.LoginRequest;
import com.cgb.coffeegourmetb.dto.request.PinLoginRequest;
import com.cgb.coffeegourmetb.dto.response.AuthenticationResponse;
import com.cgb.coffeegourmetb.service.interfaces.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(
        name = "Autenticación",
        description = "API para la autenticación de usuarios mediante contraseña o PIN."
)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
            summary = "Iniciar sesión con usuario y contraseña",
            description = "Autentica un usuario activo mediante sus credenciales administrativas."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Autenticación exitosa"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Información de autenticación inválida"
            )
    })
    @PostMapping("/login")
    public AuthenticationResponse login(
            @Valid @RequestBody LoginRequest request) {

        return authService.login(request);
    }

    @Operation(
            summary = "Iniciar sesión mediante PIN",
            description = "Autentica un usuario activo mediante PIN y valida que tenga el permiso OPERAR_CAJA."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Autenticación mediante PIN exitosa"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "PIN inválido o usuario sin permiso para operar caja"
            )
    })
    @PostMapping("/pin")
    public AuthenticationResponse loginWithPin(
            @Valid @RequestBody PinLoginRequest request) {

        return authService.loginWithPin(request);
    }
}