package com.gualberto.ronei.rmgschoolapi.application.rest.controller.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import com.gualberto.ronei.rmgschoolapi.domain.user.UserRepository;
import com.gualberto.ronei.rmgschoolapi.infra.authentication.Credential;
import com.gualberto.ronei.rmgschoolapi.infra.constants.SecurityConstants;
import com.gualberto.ronei.rmgschoolapi.infra.tests.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AuthControllerIT extends BaseIntegrationTest {


    private static final String USER_EMAIL = "user" + UUID.randomUUID() + "@email.com";
    private static final String USER_FIRST_NAME = "User";
    private static final String USER_LAST_NAME = "User";
    private static final String USER_PASSWORD = "Password";
    private static final String USER_WRONG_PASSWORD = "Wrong Password";
    public static final String API_AUTH_TOKEN = "/api/v1/auth/token";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    @Transactional
    void shouldAutenticatedByCredential() throws Exception {


        User user = User.builder()
                .email(USER_EMAIL)
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .password(passwordEncoder.encode(USER_PASSWORD))
                .build();

        userRepository.save(user);

        Credential credential = Credential.builder()
                .username(USER_EMAIL)
                .password(USER_PASSWORD)
                .build();

        mockMvc.perform(post(API_AUTH_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(credential)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value(SecurityConstants.BEARER_TOKEN))
                .andExpect(jsonPath("$.accessToken").isNotEmpty());

    }

    @Test
    @Transactional
    void shouldNotAuthenticatedWithWrongPassword() throws Exception {


        User user = User.builder()
                .email(USER_EMAIL)
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .password(passwordEncoder.encode(USER_PASSWORD))
                .build();

        userRepository.save(user);

        Credential credential = Credential.builder()
                .username(USER_EMAIL)
                .password(USER_WRONG_PASSWORD)
                .build();

        mockMvc.perform(post(API_AUTH_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(credential)))
                .andExpect(status().isBadRequest());


    }


}