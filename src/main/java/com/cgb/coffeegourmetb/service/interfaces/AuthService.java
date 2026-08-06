package com.cgb.coffeegourmetb.service.interfaces;

import com.cgb.coffeegourmetb.dto.request.LoginRequest;
import com.cgb.coffeegourmetb.dto.request.PinLoginRequest;
import com.cgb.coffeegourmetb.dto.response.AuthenticationResponse;

public interface AuthService {

    AuthenticationResponse login(LoginRequest request);

    AuthenticationResponse loginWithPin(PinLoginRequest request);
}