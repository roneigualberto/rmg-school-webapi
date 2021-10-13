package com.gualberto.ronei.rmgschoolapi.domain.user;

import com.gualberto.ronei.rmgschoolapi.domain.shared.exception.DomainException;
import com.gualberto.ronei.rmgschoolapi.domain.shared.message.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.gualberto.ronei.rmgschoolapi.domain.user.UserMessageCode.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private MessageService messageService;

    private final HashGenerator hashGenerator;


    @Override
    public User createUser(UserForm userForm) {

        Optional<User> optUser = userRepository.findByEmail(userForm.getEmail());

        if (optUser.isPresent()) {
            String message = messageService.getMessage(USER_NOT_FOUND);
            throw new DomainException(USER_NOT_FOUND, message);
        }


        User user = User.builder().email(userForm.getEmail())
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .password(hashGenerator.hash(userForm.getPassword()))
                .build();
        userRepository.save(user);

        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }
}