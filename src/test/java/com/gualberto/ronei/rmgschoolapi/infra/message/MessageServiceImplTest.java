package com.gualberto.ronei.rmgschoolapi.infra.message;


import com.gualberto.ronei.rmgschoolapi.domain.shared.message.MessageCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessageServiceImplTest {


    @InjectMocks
    private MessageServiceImpl messageService;


    @Mock
    private MessageSource messageSource;


    @Test
    public void getMessage_shouldReturnMessage() {

        MessageCode messageCode = () -> "CODE";

        Locale locale = LocaleContextHolder.getLocale();

        Object[] args = {};

        when(messageSource.getMessage(messageCode.getCode(), args, locale)).thenReturn("MESSAGE");

        String message = messageService.getMessage(messageCode);

        assertThat(message).isEqualTo("MESSAGE");
    }


}
