package com.gualberto.ronei.rmgschoolapi.application.rest.controller.purchase;


import com.gualberto.ronei.rmgschoolapi.application.rest.controller.course.*;
import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.CourseForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.CourseService;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.Lecture;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.LectureForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.SectionForm;
import com.gualberto.ronei.rmgschoolapi.domain.purchase.Purchase;
import com.gualberto.ronei.rmgschoolapi.domain.purchase.PurchaseForm;
import com.gualberto.ronei.rmgschoolapi.domain.purchase.PurchaseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

import static com.gualberto.ronei.rmgschoolapi.infra.constants.SecurityConstants.SCHEME_BEARER_AUTH;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/purchases")
@AllArgsConstructor
@SecurityRequirement(name = SCHEME_BEARER_AUTH)
@Slf4j
public class PurchaseController {


    private final PurchaseService purchaseService;

    private final PurchaseMapper purchaseMapper;



    @Transactional
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody PurchaseRequest request) {

        PurchaseForm form = purchaseMapper.toPurchaseForm(request);

        Purchase purchase = purchaseService.create(form);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(purchase.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


}
