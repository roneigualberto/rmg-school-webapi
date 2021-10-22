package com.gualberto.ronei.rmgschoolapi.domain.purchase;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
