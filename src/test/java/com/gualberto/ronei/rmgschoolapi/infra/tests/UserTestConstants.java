package com.gualberto.ronei.rmgschoolapi.infra.tests;

import com.gualberto.ronei.rmgschoolapi.domain.user.User;

public class UserTestConstants {

    public static final Long USER_ID = 1L;
    public static final String USER_EMAIL = "user@gmail.com";
    public static final String USER_FIRST_NAME = "First Name";
    public static final String USER_LAST_NAME = "Last Name";
    public static final String USER_PASSWORD = "123";
    public static final String HASH_PASSWORD = "$2a$10$yYMOzoHo7G6E.mxUf32APOEk3D190pSVGdFwBOhe6ESmf1gImcqD6";

    public static final User DEFAULT_USER;

    static {
        DEFAULT_USER = User.builder()
                .email(USER_EMAIL)
                .password(HASH_PASSWORD)
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .email(USER_EMAIL)
                .build();
    }
}