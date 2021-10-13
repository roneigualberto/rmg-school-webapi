package com.gualberto.ronei.rmgschoolapi.infra.authentication;

public interface AuthenticationService {

    Token authenticate(Credential credential);

}