package com.gualberto.ronei.rmgschoolapi.domain.category;

import com.gualberto.ronei.rmgschoolapi.domain.shared.message.MessageCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryMessageCode implements MessageCode {

    CATEGORY_NOT_FOUND(CategoryMessageCode.CATEGORY_NOT_FOUND_CODE),
    CATEGORY_NAME_ALREADY_EXISTS(CategoryMessageCode.CATEGORY_ALREADY_EXISTS_CODE),
    SUB_CATEGORY_NAME_ALREADY_EXISTS(CategoryMessageCode.SUB_CATEGORY_ALREADY_EXISTS_CODE),
    SUB_CATEGORY_NOT_FOUND(CategoryMessageCode.CATEGORY_NOT_FOUND_CODE);

    private static final String CATEGORY_NOT_FOUND_CODE = "category.not-found";
    private static final String CATEGORY_ALREADY_EXISTS_CODE = "category.name-already-exists";
    private static final String SUB_CATEGORY_ALREADY_EXISTS_CODE = "category.subcategory.name-already-exists";
    private static final String SUB_CATEGORY_NOT_FOUND_CODE = "category.subcategory.not-found";

    private final String code;

}
