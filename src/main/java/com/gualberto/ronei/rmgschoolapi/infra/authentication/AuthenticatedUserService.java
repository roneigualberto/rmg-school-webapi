package com.gualberto.ronei.rmgschoolapi.infra.authentication;

import com.gualberto.ronei.rmgschoolapi.domain.user.LoggedUserContext;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticatedUserService extends UserDetailsService, LoggedUserContext {


    AuthenticatedUser getAuthenticatedUser();

}