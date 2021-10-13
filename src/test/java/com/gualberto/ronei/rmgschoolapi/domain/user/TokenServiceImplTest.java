package com.gualberto.ronei.rmgschoolapi.domain.user;

import com.gualberto.ronei.rmgschoolapi.infra.authentication.AuthenticatedUser;
import com.gualberto.ronei.rmgschoolapi.infra.authentication.JwtConfig;
import com.gualberto.ronei.rmgschoolapi.infra.authentication.Token;
import com.gualberto.ronei.rmgschoolapi.infra.authentication.TokenServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants.USER_EMAIL;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants.USER_FIRST_NAME;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants.USER_LAST_NAME;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants.USER_PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenServiceImplTest {

    @InjectMocks
    private TokenServiceImpl tokenService;

    @Mock
    private JwtConfig jwtConfig;


    @Test
    void shouldGenerateToken() {
        User user = User.builder().email(USER_EMAIL)
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .password(USER_PASSWORD)
                .build();


        when(jwtConfig.getSecret()).thenReturn("secret");
        when(jwtConfig.getAccessTokenExpiration()).thenReturn("8650000");


        AuthenticatedUser authenticatedUser = new AuthenticatedUser(user);

        Token token = tokenService.generateToken(authenticatedUser);


        assertThat(token.getAccessToken()).isNotNull();
    }

    @Test
    void shouldValidateToken() {
        User user = User.builder().email(USER_EMAIL)
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .password(USER_PASSWORD)
                .build();


        when(jwtConfig.getSecret()).thenReturn("secret");
        when(jwtConfig.getAccessTokenExpiration()).thenReturn("8650000");


        AuthenticatedUser authenticatedUser = new AuthenticatedUser(user);

        Token token = tokenService.generateToken(authenticatedUser);

        boolean validToken = tokenService.isValidToken(token.getAccessToken());


        assertThat(validToken).isTrue();


    }

    @Test
    void shouldNotValidateToken() throws Exception {
        User user = User.builder().email(USER_EMAIL)
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .password(USER_PASSWORD)
                .build();

        when(jwtConfig.getSecret()).thenReturn("secret");
        when(jwtConfig.getAccessTokenExpiration()).thenReturn("1000");


        AuthenticatedUser authenticatedUser = new AuthenticatedUser(user);

        Token token = tokenService.generateToken(authenticatedUser);

        Thread.sleep(2000);

        boolean validToken = tokenService.isValidToken(token.getAccessToken());

        assertThat(validToken).isFalse();
    }
}