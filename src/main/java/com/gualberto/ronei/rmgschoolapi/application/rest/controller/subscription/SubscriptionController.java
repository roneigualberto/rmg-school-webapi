package com.gualberto.ronei.rmgschoolapi.application.rest.controller.subscription;


import com.gualberto.ronei.rmgschoolapi.application.rest.controller.course.CourseResponse;
import com.gualberto.ronei.rmgschoolapi.domain.subscription.Subscription;
import com.gualberto.ronei.rmgschoolapi.domain.subscription.SubscriptionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gualberto.ronei.rmgschoolapi.infra.constants.SecurityConstants.SCHEME_BEARER_AUTH;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/subscriptions")
@AllArgsConstructor
@SecurityRequirement(name = SCHEME_BEARER_AUTH)
@Slf4j
public class SubscriptionController {


    private final SubscriptionService subscriptionService;

    private final SubscriptionMapper subscriptionMapper;


    @GetMapping("/me")
    public CollectionModel<?> getMySubscriptions() {

        List<Subscription> subscriptions = subscriptionService.getLoggedStudentSubscriptions();

        List<SubscriptionResponse> responseList = subscriptionMapper.fromSubscriptions(subscriptions);

        Link selfLink = linkTo(methodOn(getClass()).getMySubscriptions()).withSelfRel();

        return CollectionModel.of(responseList, selfLink);
    }


    @Transactional
    @PatchMapping("{subscriptionId}/completed-lecture/{lectureId}")
    public ResponseEntity<?> completeLecture(@PathVariable Long subscriptionId, @PathVariable Long lectureId) {

        subscriptionService.completeLecture(subscriptionId, lectureId);

        return ResponseEntity.ok().build();
    }

    @Transactional
    @DeleteMapping("{subscriptionId}/completed-lecture/{lectureId}")
    public ResponseEntity<?> deleteCompletedLecture(@PathVariable Long subscriptionId, @PathVariable Long lectureId) {

        subscriptionService.deleteCompletedLecture(subscriptionId, lectureId);

        return ResponseEntity.ok().build();
    }


}
