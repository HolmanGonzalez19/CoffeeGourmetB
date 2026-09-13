package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.request.CreateUserRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateUserRequest;
import com.cgb.coffeegourmetb.dto.response.OperatorResponse;
import com.cgb.coffeegourmetb.dto.response.UserResponse;
import com.cgb.coffeegourmetb.entity.Role;
import com.cgb.coffeegourmetb.entity.User;
import com.cgb.coffeegourmetb.exception.BusinessException;
import com.cgb.coffeegourmetb.exception.ResourceNotFoundException;
import com.cgb.coffeegourmetb.mapper.UserMapper;
import com.cgb.coffeegourmetb.repository.RoleRepository;
import com.cgb.coffeegourmetb.repository.UserRepository;
import com.cgb.coffeegourmetb.service.interfaces.CredentialService;
import com.cgb.coffeegourmetb.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final CredentialService credentialService;

    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            UserMapper userMapper,
            CredentialService credentialService) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.credentialService = credentialService;
    }

    @Override
    public List<UserResponse> findAll() {

        return userRepository.findByActivoTrue()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public List<UserResponse> findAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public List<UserResponse> findAllInactive() {

        return userRepository.findByActivoFalse()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse findById(Long id) {

        User user = userRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un usuario activo con id: " + id));

        return userMapper.toResponse(user);
    }

    @Override
    public List<OperatorResponse> findOperators() {

        return userRepository.findActiveOperators();
    }

    @Override
    public UserResponse create(CreateUserRequest request) {
        validateUsername(request.getUsuario());
        Role role = findRole(request.getRolId());
        validarCredenciales( request.getPassword(), request.getPin());

        User user = userMapper.toEntity(request);
        user.setRole(role);
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPasswordHash( credentialService.encode( request.getPassword()));
        } else {
            user.setPasswordHash(null);
        }
        if (request.getPin() != null && !request.getPin().isBlank()) {
            user.setPinHash( credentialService.encode( request.getPin()));
        } else {
            user.setPinHash(null);
        }
        return userMapper.toResponse( userRepository.save(user));
    }

    @Override
    public UserResponse update(
            Long id,
            UpdateUserRequest request) {

        User user = findUser(id);

        validateUsernameForUpdate(
                request.getUsuario(),
                id);

        Role role = findRole(request.getRolId());

        userMapper.updateEntity(
                request,
                user);

        user.setRole(role);

        if (request.getPassword() != null &&
                !request.getPassword().isBlank()) {

            user.setPasswordHash(
                    credentialService.encode(
                            request.getPassword()));
        }

        if (request.getPin() != null &&
                !request.getPin().isBlank()) {

            user.setPinHash(
                    credentialService.encode(
                            request.getPin()));
        }

        return userMapper.toResponse(
                userRepository.save(user));
    }

    @Override
    public void activate(Long id) {

        User user = findUser(id);

        user.setActivo(true);

        userRepository.save(user);
    }

    @Override
    public void deactivate(Long id) {

        User user = findUser(id);

        user.setActivo(false);

        userRepository.save(user);
    }

    private User findUser(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un usuario con id: " + id));
    }

    private Role findRole(Long id) {

        return roleRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un rol activo con id: " + id));
    }

    private void validateUsername(String usuario) {

        if (userRepository.existsByUsuario(usuario)) {

            throw new BusinessException(
                    "Ya existe un usuario con ese nombre.");
        }
    }

    private void validateUsernameForUpdate(
            String usuario,
            Long id) {

        if (userRepository.existsByUsuarioAndIdNot(
                usuario,
                id)) {

            throw new BusinessException(
                    "Ya existe otro usuario con ese nombre.");
        }
    }

    private void validarCredenciales(
            String password,
            String pin) {

        boolean tienePassword =
                password != null &&
                        !password.isBlank();

        boolean tienePin =
                pin != null &&
                        !pin.isBlank();

        if (!tienePassword && !tienePin) {
            throw new BusinessException(
                    "El usuario debe tener una contraseña o un PIN."
            );
        }

        if (tienePassword) {

            if (password.length() < 8 ||
                    password.length() > 20) {

                throw new BusinessException(
                        "La contraseña debe tener entre 8 y 20 caracteres."
                );
            }
        }

        if (tienePin) {
            validarPin(pin);
        }
    }

    private void validarPin(String pin) {

        if (pin == null || !pin.matches("\\d{4}")) {
            throw new BusinessException(
                    "El PIN debe tener exactamente 4 dígitos numéricos."
            );
        }

        if (pin.matches(".*(\\d)\\1\\1.*")) {
            throw new BusinessException(
                    "El PIN no puede contener 3 o más números iguales consecutivos."
            );
        }
    }
}