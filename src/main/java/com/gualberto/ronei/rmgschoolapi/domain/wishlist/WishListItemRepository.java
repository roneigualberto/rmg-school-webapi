package com.gualberto.ronei.rmgschoolapi.domain.wishlist;

import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListItemRepository extends JpaRepository<WishListItem, Long> {


    List<WishListItem> findByStudent(User student);
}
