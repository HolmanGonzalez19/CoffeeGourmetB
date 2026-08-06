package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.service.interfaces.CredentialService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CredentialServiceImpl
        implements CredentialService {

    private final PasswordEncoder passwordEncoder;

    public CredentialServiceImpl(
            PasswordEncoder passwordEncoder) {

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String value) {

        return passwordEncoder.encode(value);
    }

    @Override
    public boolean matches(
            String rawValue,
            String encodedValue) {

        return passwordEncoder.matches(
                rawValue,
                encodedValue);
    }
}