package com.gualberto.ronei.rmgschoolapi.application.rest.controller.shopcart;


import com.gualberto.ronei.rmgschoolapi.domain.shopcart.ShopCart;
import com.gualberto.ronei.rmgschoolapi.domain.shopcart.ShopCartItem;
import com.gualberto.ronei.rmgschoolapi.domain.shopcart.ShopCartService;
import com.gualberto.ronei.rmgschoolapi.domain.user.LoggedUserContext;
import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.gualberto.ronei.rmgschoolapi.infra.constants.SecurityConstants.SCHEME_BEARER_AUTH;

@RestController
@RequestMapping("/api/v1/schop-cart")
@AllArgsConstructor
@SecurityRequirement(name = SCHEME_BEARER_AUTH)
@Slf4j
public class SchopCartController {

    private final ShopCartService shopCartService;

    private final LoggedUserContext loggedUserContext;

    private final ShopCartMapper shopCartMapper;


    @Transactional
    @GetMapping()
    public ResponseEntity<ShopCartResponse> get() {

        User loggedUser = loggedUserContext.getLoggedUser();

        Optional<ShopCart> shopCart = shopCartService.get(loggedUser.getId());


        ShopCartResponse response = shopCartMapper.fromShopCart(shopCart.get());

        return ResponseEntity.ok(response);
    }


    @Transactional
    @PostMapping("/item/{courseId}")
    public ResponseEntity<ShopCartItemResponse> addItem(@PathVariable Long courseId) {

        User loggedUser = loggedUserContext.getLoggedUser();

        ShopCartItem shopCartItem = shopCartService.addItem(loggedUser.getId(), courseId);


        ShopCartItemResponse response = shopCartMapper.fromShopCartItem(shopCartItem);


        return ResponseEntity.ok(response);
    }


    @Transactional
    @DeleteMapping("/item/{courseId}")
    public ResponseEntity<?> delete(@PathVariable Long courseId) {

        User loggedUser = loggedUserContext.getLoggedUser();

        shopCartService.deleteItem(loggedUser.getId(), courseId);

        return ResponseEntity.noContent().build();
    }

}
