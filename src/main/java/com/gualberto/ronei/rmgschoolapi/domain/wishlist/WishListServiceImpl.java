package com.gualberto.ronei.rmgschoolapi.domain.wishlist;

import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.CourseService;
import com.gualberto.ronei.rmgschoolapi.domain.user.LoggedUserContext;
import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WishListServiceImpl implements WishListItemService {


    private final WishListItemRepository wishListItemRepository;

    private final LoggedUserContext loggedUserContext;

    private final CourseService courseService;

    @Override
    public List<WishListItem> getMyWishListItems() {
        User student = loggedUserContext.getLoggedUser();
        return wishListItemRepository.findByStudent(student);
    }

    @Override
    public void addWishListItem(Long courseId) {

        Course course = courseService.get(courseId);

        User student = loggedUserContext.getLoggedUser();

        WishListItem wishListItem = WishListItem.builder().student(student).course(course).build();

        wishListItemRepository.save(wishListItem);
    }

    @Override
    public void deleteById(Long wishListItemId) {
        wishListItemRepository.deleteById(wishListItemId);
    }


}
