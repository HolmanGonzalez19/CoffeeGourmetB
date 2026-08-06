package com.cgb.coffeegourmetb.service.interfaces;

public interface CredentialService {

    String encode(String value);

    boolean matches(
            String rawValue,
            String encodedValue);
}