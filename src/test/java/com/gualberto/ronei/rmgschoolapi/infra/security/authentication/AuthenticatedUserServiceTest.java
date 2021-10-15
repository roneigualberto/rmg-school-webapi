package com.gualberto.ronei.rmgschoolapi.infra.security.authentication;


import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import com.gualberto.ronei.rmgschoolapi.domain.user.UserService;
import com.gualberto.ronei.rmgschoolapi.infra.authentication.AutenticatedUserServiceImpl;
import com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants.HASH_PASSWORD;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants.USER_EMAIL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticatedUserServiceTest {


    @InjectMocks
    private AutenticatedUserServiceImpl authenticatedUserService;

    @Mock
    private UserService userService;

    @Test
    public void shouldNotReturnUserIdentity_userNotFound() {
        assertThrows(UsernameNotFoundException.class, () -> authenticatedUserService.loadUserByUsername(USER_EMAIL));
    }

    @Test
    public void shouldReturnUserIdentity_userFound() {
        User existentUser = UserTestConstants.DEFAULT_USER;

        when(userService.findByEmail(USER_EMAIL)).thenReturn(Optional.of(existentUser));

        UserDetails userDetails = authenticatedUserService.loadUserByUsername(USER_EMAIL);

        assertThat(userDetails.getUsername()).isEqualTo(USER_EMAIL);
        assertThat(userDetails.getPassword()).isEqualTo(HASH_PASSWORD);


    }

}