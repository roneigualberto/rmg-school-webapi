package com.gualberto.ronei.rmgschoolapi.application.rest.controller.subscription;


import com.gualberto.ronei.rmgschoolapi.application.rest.controller.purchase.PurchaseMapper;
import com.gualberto.ronei.rmgschoolapi.application.rest.controller.purchase.PurchaseRequest;
import com.gualberto.ronei.rmgschoolapi.domain.purchase.Purchase;
import com.gualberto.ronei.rmgschoolapi.domain.purchase.PurchaseForm;
import com.gualberto.ronei.rmgschoolapi.domain.purchase.PurchaseService;
import com.gualberto.ronei.rmgschoolapi.domain.subscription.SubscriptionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.gualberto.ronei.rmgschoolapi.infra.constants.SecurityConstants.SCHEME_BEARER_AUTH;

@RestController
@RequestMapping("/api/v1/subscriptions/{subscriptionId}")
@AllArgsConstructor
@SecurityRequirement(name = SCHEME_BEARER_AUTH)
@Slf4j
public class SubscriptionController {


    private final SubscriptionService subscriptionService;


    @Transactional
    @PatchMapping("completed-lecture/{lectureId}")
    public ResponseEntity<?> completeLecture(@PathVariable Long subscriptionId, @PathVariable Long lectureId) {

        subscriptionService.completeLecture(subscriptionId, lectureId);

        return ResponseEntity.ok().build();
    }

    @Transactional
    @DeleteMapping("completed-lecture/{lectureId}")
    public ResponseEntity<?> deleteCompletedLecture(@PathVariable Long subscriptionId, @PathVariable Long lectureId) {

        subscriptionService.deleteCompletedLecture(subscriptionId, lectureId);

        return ResponseEntity.ok().build();
    }


}
