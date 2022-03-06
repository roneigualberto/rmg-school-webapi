package com.gualberto.ronei.rmgschoolapi.domain.wishlist;

import java.util.List;

public interface WishListItemService {


    List<WishListItem> getMyWishListItems();


    void addWishListItem(Long courseId);

    void deleteById(Long wishListItemId);


}
