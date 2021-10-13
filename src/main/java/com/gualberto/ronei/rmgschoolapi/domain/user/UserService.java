package com.gualberto.ronei.rmgschoolapi.domain.user;

import java.util.Optional;

public interface UserService {


    User createUser(UserForm user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long userId);

}