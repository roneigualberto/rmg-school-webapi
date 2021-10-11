package com.gualberto.ronei.rmgschoolapi.domain.common;

import java.util.Locale;
import java.util.function.Function;

public interface MessageService {

    String getMessage(MessageCode code, Locale locale, Object... args);

    String getMessage(MessageCode code, Object... args);

    default <X extends Throwable> void toThrowable(MessageCode code, Locale locale, Function<String, ? extends X> exceptionMapper, Object... args) throws X {
        String message = getMessage(code, locale, args);
        throw exceptionMapper.apply(message);
    }

    default <X extends Throwable> X toThrowable(MessageCode code, Function<String, ? extends X> exceptionMapper, Object... args) throws X {
        String message = getMessage(code, args);
        return exceptionMapper.apply(message);
    }
}
