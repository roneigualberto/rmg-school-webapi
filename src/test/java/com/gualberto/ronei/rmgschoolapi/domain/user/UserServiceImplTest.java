package com.gualberto.ronei.rmgschoolapi.domain.user;

import com.gualberto.ronei.rmgschoolapi.domain.shared.exception.DomainException;
import com.gualberto.ronei.rmgschoolapi.domain.shared.message.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants.HASH_PASSWORD;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants.USER_EMAIL;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants.USER_FIRST_NAME;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants.USER_LAST_NAME;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants.USER_PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private HashGenerator hashGenerator;

    @Mock
    private MessageService messageService;


    @Test
    void shouldCreateUser() {
        //given
        UserForm userForm = UserForm.builder().email(USER_EMAIL)
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .password(USER_PASSWORD)
                .build();

        when(hashGenerator.hash(any())).thenReturn(HASH_PASSWORD);

        //when
        User user = userService.createUser(userForm);

        //then
        assertThat(user.getEmail()).isEqualTo(userForm.getEmail());
        assertThat(user.getFirstName()).isEqualTo(userForm.getFirstName());
        assertThat(user.getLastName()).isEqualTo(userForm.getLastName());
        assertThat(user.getPassword()).isEqualTo(HASH_PASSWORD);

        verify(userRepository, times(1)).save(any());

    }

    @Test
    void shouldNotCreateUser_ifUserAlreadyExists() {
        //given
        UserForm userForm = UserForm.builder().email(USER_EMAIL)
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .password(USER_PASSWORD)
                .build();

        User existentUser = User.builder()
                .email(USER_EMAIL)
                .build();

        when(userRepository.findByEmail(any())).thenReturn(Optional.of(existentUser));

        assertThrows(DomainException.class, () -> userService.createUser(userForm));

    }


}