package com.gualberto.ronei.rmgschoolapi.infra.tests;

import com.gualberto.ronei.rmgschoolapi.domain.category.Category;
import com.gualberto.ronei.rmgschoolapi.domain.category.SubCategory;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;

public final class CategoryTestContants {

    public static final String CATEGORY_NAME = "Category Name";
    public static final String CATEGORY_NAME_FOR_UPDATE = "Category Name For Update";
    public static final Long CATEGORY_ID = 1L;
    public static final Long OTHER_CATEGORY_ID = 2L;
    public static final String OTHER_CATEGORY_NAME = "Other Category Name";


    public static final String MSG_CATEGORY_NAME_EXISTS = "Category Name Already Exists";
    public static final String MSG_SUB_CATEGORY_NAME_EXISTS = "Sub Category Name Already Exists";
    public static final String MSG_CATEGORY_NOT_FOUND = "Category Not Found";


    public static final Long SUB_CATEGORY_ID = 12L;
    public static final String SUB_CATEGORY_NAME = "Sub Category Name";
    public static final String OTHER_SUB_CATEGORY_NAME = "Other Sub Category Name";


    public static final Category DEFAULT_CATEGORY;

    public static final SubCategory DEFAULT_SUB_CATEGORY;



    static {
        DEFAULT_CATEGORY = Category
                .builder()
                .id(CATEGORY_ID)
                .name(CATEGORY_NAME)
                .build();

        DEFAULT_SUB_CATEGORY = SubCategory
                .builder()
                .category(DEFAULT_CATEGORY)
                .id(SUB_CATEGORY_ID)
                .name(SUB_CATEGORY_NAME)
                .build();

    }

}
