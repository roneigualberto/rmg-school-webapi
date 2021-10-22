package com.gualberto.ronei.rmgschoolapi.domain.purchase;


import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.CourseRepository;
import com.gualberto.ronei.rmgschoolapi.domain.user.LoggedUserContext;
import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final LoggedUserContext loggedUserContext;

    private final PurchaseRepository purchaseRepository;

    private final CourseRepository courseRepository;

    private final PaymentMethodRepository paymentMethodRepository;


    @Override
    public Purchase create(PurchaseForm purchaseForm) {

        User owner = loggedUserContext.getLoggedUser();

        List<Course> courses = courseRepository.findAllById(purchaseForm.getCourseIds());

        PaymentForm paymentForm = purchaseForm.getPayment();


        Payment payment = Payment.builder()
                .cardHolderName(paymentForm.getCardHolderName())
                .cardNumber(paymentForm.getCardNumber())
                .expirationMonth(paymentForm.getExpirationMonth())
                .expirationYear(paymentForm.getExpirationYear())
                .type(paymentForm.getType())
                .installments(paymentForm.getInstallments())
                .cvv(paymentForm.getCvv())
                .build();

        Purchase purchase = Purchase.builder()
                .payment(payment)
                .owner(owner)
                .build();

        purchase.addCourses(courses);

        purchaseRepository.save(purchase);

        if (paymentForm.isShouldSave()) {
            PaymentMethod paymentMethod = PaymentMethod.builder()
                    .owner(owner)
                    .cardHolderName(paymentForm.getCardHolderName())
                    .cardNumber(paymentForm.getCardNumber())
                    .expirationMonth(paymentForm.getExpirationMonth())
                    .expirationYear(paymentForm.getExpirationYear())
                    .type(paymentForm.getType())
                    .cvv(paymentForm.getCvv())
                    .build();
            paymentMethodRepository.save(paymentMethod);
        }


        return purchase;
    }
}
