package com.cgb.coffeegourmetb.service.interfaces;

import com.cgb.coffeegourmetb.dto.request.CreateUserRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateUserRequest;
import com.cgb.coffeegourmetb.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> findAll();

    List<UserResponse> findAllInactive();

    UserResponse findById(Long id);

    UserResponse create(CreateUserRequest request);

    UserResponse update(Long id, UpdateUserRequest request);

    void activate(Long id);

    void deactivate(Long id);

}