package com.gualberto.ronei.rmgschoolapi.domain.subscription.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewForm {

    private Integer rating;

    private String comment;
}
