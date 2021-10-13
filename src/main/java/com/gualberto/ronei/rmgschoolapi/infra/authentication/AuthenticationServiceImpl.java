package com.gualberto.ronei.rmgschoolapi.infra.authentication;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {


    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    @Override
    public Token authenticate(Credential credential) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(credential.getUsername(), credential.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        AuthenticatedUser authenticatedUSer = (AuthenticatedUser) authentication.getPrincipal();
        return tokenService.generateToken(authenticatedUSer);
    }

}