package com.gualberto.ronei.rmgschoolapi.application.rest.controller.subscription;

import com.gualberto.ronei.rmgschoolapi.application.rest.controller.category.CategoryResponse;
import com.gualberto.ronei.rmgschoolapi.application.rest.controller.category.SubCategoryResponse;
import com.gualberto.ronei.rmgschoolapi.application.rest.controller.course.CourseResponse;
import com.gualberto.ronei.rmgschoolapi.application.rest.controller.course.SectionResponse;
import com.gualberto.ronei.rmgschoolapi.application.rest.controller.user.UserResponse;
import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.SkillLevelEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.gualberto.ronei.rmgschoolapi.application.rest.controller.category.CategoryResponse.ITEM_RELATION;
import static com.gualberto.ronei.rmgschoolapi.application.rest.controller.category.CategoryResponse.fromCategory;
import static com.gualberto.ronei.rmgschoolapi.application.rest.controller.category.SubCategoryResponse.fromSubCategory;
import static com.gualberto.ronei.rmgschoolapi.application.rest.controller.course.SectionResponse.fromSections;
import static com.gualberto.ronei.rmgschoolapi.application.rest.controller.user.UserResponse.fromUser;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Relation(itemRelation = ITEM_RELATION, collectionRelation = SubscriptionResponse.COLLECTION_RELATION)
@Builder
public class SubscriptionResponse {

    public static final String ITEM_RELATION = "subcription";

    public static final String COLLECTION_RELATION = "subcriptions";

    private Long id;

    private UserResponse student;

    private CourseResponse course;

    private LocalDateTime createdAt;

    private Boolean finished;

    private LocalDateTime finishedDate;


}