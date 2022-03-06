package com.gualberto.ronei.rmgschoolapi.domain.subscription.review;

import com.gualberto.ronei.rmgschoolapi.domain.subscription.Subscription;
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
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false)
    private Subscription subscription;

    @Column(nullable = false)
    private Integer rating;

    @Column
    private String comment;
}
