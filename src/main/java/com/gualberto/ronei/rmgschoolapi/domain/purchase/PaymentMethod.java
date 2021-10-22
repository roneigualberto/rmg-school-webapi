package com.gualberto.ronei.rmgschoolapi.domain.purchase;


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
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User owner;

    @Column(nullable = true)
    private String cardHolderName;

    @Enumerated(EnumType.STRING)
    private PaymentTypeEnum type;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private Integer expirationYear;

    @Column(nullable = false)
    private Integer expirationMonth;

    @Column(nullable = false)
    private String cvv;
}
