package com.cgb.coffeegourmetb.mapper;

import com.cgb.coffeegourmetb.dto.request.CreateUserRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateUserRequest;
import com.cgb.coffeegourmetb.dto.response.UserResponse;
import com.cgb.coffeegourmetb.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(CreateUserRequest request) {

        User user = new User();

        user.setNombre(request.getNombre());
        user.setUsuario(request.getUsuario());

        // Más adelante aquí se almacenará el hash BCrypt
        user.setPasswordHash(request.getPassword());

        user.setActivo(true);

        return user;
    }

    public void updateEntity(UpdateUserRequest request,
                             User user) {

        user.setNombre(request.getNombre());
        user.setUsuario(request.getUsuario());

        if (request.getPassword() != null &&
                !request.getPassword().isBlank()) {

            user.setPasswordHash(request.getPassword());
        }

        user.setActivo(request.getActivo());
    }

    public UserResponse toResponse(User user) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());

        response.setRolId(user.getRole().getId());
        response.setRolNombre(user.getRole().getNombre());

        response.setNombre(user.getNombre());
        response.setUsuario(user.getUsuario());

        response.setActivo(user.getActivo());

        response.setFechaCreacion(user.getFechaCreacion());
        response.setFechaActualizacion(user.getFechaActualizacion());

        return response;
    }

}