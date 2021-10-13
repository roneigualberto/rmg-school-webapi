package com.gualberto.ronei.rmgschoolapi.application.rest.controller.user;


import com.gualberto.ronei.rmgschoolapi.domain.user.UserForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserRequest {

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    public UserForm toUserForm() {
        return new UserForm(email, firstName, lastName, password);
    }
}
