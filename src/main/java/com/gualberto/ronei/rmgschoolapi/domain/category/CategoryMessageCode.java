package com.gualberto.ronei.rmgschoolapi.domain.category;

import com.gualberto.ronei.rmgschoolapi.domain.shared.message.MessageCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryMessageCode implements MessageCode {

    CATEGORY_NOT_FOUND(CategoryMessageCode.CATEGORY_NOT_FOUND_CODE),
    CATEGORY_ALREADY_EXISTS(CategoryMessageCode.CATEGORY_ALREADY_EXISTS_CODE);

    private static final String CATEGORY_NOT_FOUND_CODE = "category.not-found";
    private static final String CATEGORY_ALREADY_EXISTS_CODE = "category.already-exists";

    private String code;

}
