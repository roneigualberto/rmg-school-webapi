package com.gualberto.ronei.rmgschoolapi.domain.shared.message;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    public static final String CODE_TEST = "CODE";
    public static final String MESSAGE_TEST = "MESSAGE";
    @Spy
    private MessageService messageServiceSpy;


    @Test
    public void toThrowable_shouldBuildThrowable() {

        MessageCode messageCode = () -> CODE_TEST;

        doReturn(MESSAGE_TEST).when(messageServiceSpy).getMessage(messageCode);

        RuntimeException runtimeException = messageServiceSpy.toThrowable(messageCode, RuntimeException::new);

        assertThat(runtimeException.getMessage()).isEqualTo(MESSAGE_TEST);
    }
}
