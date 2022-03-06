package com.gualberto.ronei.rmgschoolapi.application.rest.controller.purchase;

import com.gualberto.ronei.rmgschoolapi.application.rest.controller.course.*;
import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.CourseForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.Lecture;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.LectureForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.SectionForm;
import com.gualberto.ronei.rmgschoolapi.domain.purchase.PurchaseForm;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {


    PurchaseForm toPurchaseForm(PurchaseRequest purchaseRequest);

}
