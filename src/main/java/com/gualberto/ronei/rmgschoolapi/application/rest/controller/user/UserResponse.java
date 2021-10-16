package com.gualberto.ronei.rmgschoolapi.application.rest.controller.user;

import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponse {


    private Long id;

    private String email;

    private String firstName;

    private String lastName;


    public static UserResponse fromUser(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName());

    }
}