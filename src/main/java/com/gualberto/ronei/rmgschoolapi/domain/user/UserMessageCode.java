package com.gualberto.ronei.rmgschoolapi.domain.user;

import com.gualberto.ronei.rmgschoolapi.domain.shared.message.MessageCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserMessageCode implements MessageCode {

    USER_NOT_FOUND(UserMessageCode.USER_NOT_FOUND_CODE);


    private static final String USER_NOT_FOUND_CODE = "user.not-found";

    private final String code;

}
