package com.gualberto.ronei.rmgschoolapi.domain.purchase;

import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User owner;

    @CreationTimestamp
    private LocalDateTime purchaseDate;

    @Column(nullable = false)
    private Double total;

    @Embedded
    private Payment payment;

    private List<PurchaseItem> items = new ArrayList<>();


    public void addCourses(List<Course> courses) {

        items = courses.stream().map((course) ->
                PurchaseItem.builder()
                        .course(course)
                        .price(course.getPrice())
                        .build()
        ).collect(Collectors.toList());

        this.total = items.stream().mapToDouble(PurchaseItem::getPrice).sum();

    }
}
