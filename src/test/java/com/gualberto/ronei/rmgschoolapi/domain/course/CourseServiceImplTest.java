package com.gualberto.ronei.rmgschoolapi.domain.course;

import com.gualberto.ronei.rmgschoolapi.domain.category.Category;
import com.gualberto.ronei.rmgschoolapi.domain.category.CategoryService;
import com.gualberto.ronei.rmgschoolapi.domain.category.SubCategory;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.Lecture;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.LectureForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.LectureRepository;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.LectureType;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.SectionForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.SectionRepository;
import com.gualberto.ronei.rmgschoolapi.domain.shared.exception.DomainException;
import com.gualberto.ronei.rmgschoolapi.domain.user.LoggedUserContext;
import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import com.gualberto.ronei.rmgschoolapi.infra.tests.CategoryTestContants;
import com.gualberto.ronei.rmgschoolapi.infra.tests.CourseTestConstants;
import com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.gualberto.ronei.rmgschoolapi.infra.tests.CategoryTestContants.CATEGORY_ID;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CategoryTestContants.SUB_CATEGORY_ID;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CourseTestConstants.*;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants.SECOND_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    private LoggedUserContext loggedUserContext;

    @Mock
    private SectionRepository sectionRepository;

    @Mock
    private LectureRepository lectureRepository;

    private CourseForm courseForm;
    private Course courseResult;
    private Category categoryTest = CategoryTestContants.DEFAULT_CATEGORY;
    private SubCategory subCategoryTest = CategoryTestContants.DEFAULT_SUB_CATEGORY;
    private Course courseTest = CourseTestConstants.DEFAULT_COURSE;
    private SectionForm sectionForm;
    private Section sectionResult;
    private Section section1Test = CourseTestConstants.SECTION_1;
    private LectureForm lectureForm;
    private Lecture lectureResult;


    @Test
    @DisplayName("Creating a course with mandatory fields")
    void create_shouldCreateCourse() {
        givenCourseForm();
        whenCalling_LoggedUserContext_getLoggerUser();
        whenCalling_CategoryService_get();
        whenCalling_CategoryService_getSubCategory();
        whenCalledCreateCourse();
        thenCreateOrUpdateCategory();
    }

    @Test
    @DisplayName("Adding a section from a course")
    void addSection_shouldCreateSectionInCourse() {
        whenCalling_LoggedUserContext_getLoggerUser();
        whenCalling_CourseRepository_findById();
        givenSectionForm();
        whenCalledAddSection();
        thenShouldCreateSection();
    }

    @Test
    @DisplayName("Should not create section in course if logged user is not instructor in this course")
    void addSection_shouldNotCreateSectionIfUserDoesNotHaveCourse() {
        whenCalling_LoggedUserContext_getLoggerUser(SECOND_USER);
        whenCalling_CourseRepository_findById();
        givenSectionForm();
        assertThrows(DomainException.class, this::whenCalledAddSection);
    }

    @Test
    @DisplayName("Adding a lecture from a course")
    void addLecture_shouldCreateLectureInCourse() {
        whenCalling_LoggedUserContext_getLoggerUser();
        whenCalling_CourseRepository_findById();
        whenCalling_SectionRepository_findById();
        givenLectureForm();
        whenCalledAddLecture();
        thenShouldCreateLecture();
    }

    private void thenShouldCreateSection() {
        verify(sectionRepository, times(1)).save(any());
        assertThat(sectionResult.getName()).isEqualTo(sectionForm.getName());
        assertThat(sectionResult.getOrder()).isEqualTo(sectionForm.getOrder());
        assertThat(sectionResult.getCourse()).isEqualTo(courseTest);
    }

    private void thenShouldCreateLecture() {
        verify(lectureRepository, times(1)).save(any());
        assertThat(lectureResult.getTitle()).isEqualTo(lectureForm.getTitle());
        assertThat(lectureResult.getOrder()).isEqualTo(lectureForm.getOrder());
        assertThat(lectureResult.getSection()).isEqualTo(section1Test);
        assertThat(lectureResult.getCourse()).isEqualTo(courseTest);
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

    private void givenSectionForm() {
        sectionForm = SectionForm.builder()
                .name(SECTION_1_NAME)
                .order(SECTION_1_ORDER)
                .build();
    }

    private void givenLectureForm() {
        lectureForm = LectureForm.builder()
                .sectionId(SECTION_1_ID)
                .title(LECTURE_1_TITLE)
                .order(SECTION_1_ORDER)
                .type(LectureType.VIDEO)
                .build();
    }

    private void whenCalling_CourseRepository_findById() {
        when(courseRepository.findById(COURSE_ID)).thenReturn(Optional.of(courseTest));
    }

    private void whenCalling_SectionRepository_findById() {
        courseTest.addSection(section1Test);
        when(sectionRepository.findById(SECTION_1_ID)).thenReturn(Optional.of(section1Test));
    }

    private void whenCalling_CategoryService_get() {
        when(categoryService.get(CATEGORY_ID)).thenReturn(categoryTest);
    }

    private void whenCalling_CategoryService_getSubCategory() {
        when(categoryService.getSubCategory(SUB_CATEGORY_ID)).thenReturn(subCategoryTest);
    }

    private void whenCalling_LoggedUserContext_getLoggerUser() {
        whenCalling_LoggedUserContext_getLoggerUser(UserTestConstants.DEFAULT_USER);
    }

    private void whenCalling_LoggedUserContext_getLoggerUser(User user) {
        when(loggedUserContext.getLoggedUser()).thenReturn(user);
    }

    private void whenCalledCreateCourse() {
        courseResult = courseService.createCourse(courseForm);
    }

    private void whenCalledAddSection() {
        sectionResult = courseService.addSection(courseTest.getId(), sectionForm);
    }

    private void whenCalledAddLecture() {
        lectureResult = courseService.addLecture(courseTest.getId(), lectureForm);
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