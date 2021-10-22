package com.gualberto.ronei.rmgschoolapi.domain.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Enumerated(EnumType.STRING)
    private PaymentTypeEnum type;


    @Column(nullable = false)
    private String cardHolderName;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private Integer expirationYear;

    @Column(nullable = false)
    private Integer expirationMonth;

    @Column(nullable = false)
    private String cvv;

    @Column
    private Integer installments;
}
