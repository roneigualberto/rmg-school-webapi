package com.gualberto.ronei.rmgschoolapi.infra.tests.common;

import com.gualberto.ronei.rmgschoolapi.domain.common.MessageCode;
import com.gualberto.ronei.rmgschoolapi.domain.common.MessageService;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageServiceImpl implements MessageService {

    @Override
    public String getMessage(MessageCode code, Locale locale, Object... args) {
        return null;
    }

    @Override
    public String getMessage(MessageCode code, Object... args) {
        return null;
    }
}
