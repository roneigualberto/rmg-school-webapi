package com.gualberto.ronei.rmgschoolapi.infra.authentication;

public interface TokenService {

    Token generateToken(AuthenticatedUser autenticated);

    boolean isValidToken(String token);

    String getUsername(String token);
}