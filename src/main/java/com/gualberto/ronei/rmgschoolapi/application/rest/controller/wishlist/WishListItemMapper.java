package com.gualberto.ronei.rmgschoolapi.application.rest.controller.wishlist;

import com.gualberto.ronei.rmgschoolapi.domain.wishlist.WishListItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WishListItemMapper {


    WishListItemResponse fromWishListItem(WishListItem wishListItem);

    List<WishListItemResponse> fromWishListItems(List<WishListItem> wishListItems);


}
