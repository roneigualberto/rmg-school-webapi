package com.gualberto.ronei.rmgschoolapi.domain.category;

import com.gualberto.ronei.rmgschoolapi.domain.common.MessageCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryMessageCodeEnum implements MessageCode {

    CATEGORY_NOT_FOUND(CategoryMessageCodeEnum.CATEGORY_NOT_FOUND_CODE),
    CATEGORY_ALREADY_EXISTS(CategoryMessageCodeEnum.CATEGORY_ALREADY_EXISTS_CODE);

    private static final String CATEGORY_NOT_FOUND_CODE = "category.not-found";
    private static final String CATEGORY_ALREADY_EXISTS_CODE = "category.already-exists";

    private String code;

}
