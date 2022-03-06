package com.gualberto.ronei.rmgschoolapi.application.rest.controller.wishlist;


import com.gualberto.ronei.rmgschoolapi.domain.wishlist.WishListItem;
import com.gualberto.ronei.rmgschoolapi.domain.wishlist.WishListItemService;
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
@RequestMapping("/api/v1/wish-lists")
@AllArgsConstructor
@SecurityRequirement(name = SCHEME_BEARER_AUTH)
@Slf4j
public class WishListController {


    private final WishListItemService wishListItemService;

    private final WishListItemMapper subscriptionMapper;


    @GetMapping()
    public CollectionModel<?> getMyWishListItems() {

        List<WishListItem> wishListItems = wishListItemService.getMyWishListItems();

        List<WishListItemResponse> responseList = subscriptionMapper.fromWishListItems(wishListItems);

        Link selfLink = linkTo(methodOn(getClass()).getMyWishListItems()).withSelfRel();

        return CollectionModel.of(responseList, selfLink);
    }


    @Transactional
    @PostMapping("/course/{courseId}")
    public ResponseEntity<?> addWishListItem(@PathVariable Long courseId) {

        wishListItemService.addWishListItem(courseId);

        return ResponseEntity.ok().build();
    }

    @Transactional
    @DeleteMapping("{wishListItemId}")
    public ResponseEntity<?> removeWishListItem(@PathVariable Long wishListItemId) {

        wishListItemService.deleteById(wishListItemId);

        return ResponseEntity.noContent().build();
    }


}
