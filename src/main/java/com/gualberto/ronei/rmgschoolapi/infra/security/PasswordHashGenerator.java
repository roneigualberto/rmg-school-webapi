package com.gualberto.ronei.rmgschoolapi.infra.security;

import com.gualberto.ronei.rmgschoolapi.domain.user.HashGenerator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PasswordHashGenerator implements HashGenerator {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String hash(String plainText) {
        return passwordEncoder.encode(plainText);
    }
}