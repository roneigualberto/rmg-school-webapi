package com.gualberto.ronei.rmgschoolapi.domain.wishlist;

import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.LectureType;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User student;
}
