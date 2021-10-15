package com.gualberto.ronei.rmgschoolapi.domain.course;

import com.gualberto.ronei.rmgschoolapi.domain.category.Category;
import com.gualberto.ronei.rmgschoolapi.domain.category.CategoryService;
import com.gualberto.ronei.rmgschoolapi.domain.category.SubCategory;
import com.gualberto.ronei.rmgschoolapi.domain.user.UserService;
import com.gualberto.ronei.rmgschoolapi.infra.tests.CategoryTestContants;
import com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.gualberto.ronei.rmgschoolapi.infra.tests.CategoryTestContants.CATEGORY_ID;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CategoryTestContants.SUB_CATEGORY_ID;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CourseTestContants.COURSE_ABOUT;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CourseTestContants.COURSE_NAME;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CourseTestContants.COURSE_PRICE;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CourseTestContants.COURSE_TITLE;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants.USER_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @InjectMocks
    private CourseServiceImpl courseService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CategoryService categoryService;

    @Mock
    private UserService userService;

    private CourseForm courseForm;
    private Course courseResult;
    private Category categoryTest = CategoryTestContants.DEFAULT_CATEGORY;
    private SubCategory subCategoryTest = CategoryTestContants.DEFAULT_SUB_CATEGORY;


    @Test
    @DisplayName("Creating a course with mandatory fields")
    void create_shouldCreateCourse() {
        givenCourseForm();
        whenCalling_UserService_get();
        whenCalling_CategoryService_get();
        whenCalling_CategoryService_getSubCategory();
        whenCalledCreateCourse();
        thenCreateOrUpdateCategory();
    }

    private void givenCourseForm() {
        courseForm = CourseForm.builder()
                .name(COURSE_NAME)
                .title(COURSE_TITLE)
                .about(COURSE_ABOUT)
                .categoryId(CATEGORY_ID)
                .subCategoryId(SUB_CATEGORY_ID)
                .skillLevel(SkillLevelEnum.BEGINNER)
                .price(COURSE_PRICE)
                .build();
    }

    private void whenCalling_CategoryService_get() {
        when(categoryService.get(CATEGORY_ID)).thenReturn(categoryTest);
    }

    private void whenCalling_CategoryService_getSubCategory() {
        when(categoryService.getSubCategory(SUB_CATEGORY_ID)).thenReturn(subCategoryTest);
    }

    private void whenCalling_UserService_get() {
        when(userService.get(USER_ID)).thenReturn(UserTestConstants.DEFAULT_USER);
    }

    private void whenCalledCreateCourse() {
        courseResult = courseService.createCourse(USER_ID, courseForm);
    }

    private void thenCreateOrUpdateCategory() {
        verify(courseRepository, times(1)).save(any());
        assertThat(courseResult.getName()).isEqualTo(courseForm.getName());
        assertThat(courseResult.getTitle()).isEqualTo(courseForm.getTitle());
        assertThat(courseResult.getAbout()).isEqualTo(courseForm.getAbout());
        assertThat(courseResult.getPrice()).isEqualTo(courseForm.getPrice());
        assertThat(courseResult.getCategory()).isEqualTo(categoryTest);
        assertThat(courseResult.getSubCategory()).isEqualTo(subCategoryTest);
    }


}