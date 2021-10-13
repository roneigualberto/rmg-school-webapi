package com.gualberto.ronei.rmgschoolapi.infra.authentication;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticatedUserService extends UserDetailsService {


    AuthenticatedUser getAuthenticatedUser();

}