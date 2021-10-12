package com.gualberto.ronei.rmgschoolapi.domain.shared.exception;

import com.gualberto.ronei.rmgschoolapi.domain.shared.message.MessageCode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DomainExceptionTest {


    public static final String CODE_TEST = "CODE";
    public static final String MESSAGE_TEST = "MESSAGE";

    @Test
    void createDomainException_withStringParameter() {

        DomainException domainException = new DomainException(CODE_TEST, MESSAGE_TEST);

        assertThat(domainException.getCode()).isEqualTo(CODE_TEST);
        assertThat(domainException.getMessage()).isEqualTo(MESSAGE_TEST);

    }

    @Test
    void createDomainException_withoutCode() {

        DomainException domainException = new DomainException(MESSAGE_TEST);

        assertThat(domainException.getCode()).isNull();
        assertThat(domainException.getMessage()).isEqualTo(MESSAGE_TEST);

    }

    @Test
    void createDomainException_withMessageCode() {

        MessageCode messageCode = () -> CODE_TEST;

        DomainException domainException = new DomainException(messageCode, MESSAGE_TEST);

        assertThat(domainException.getCode()).isEqualTo(CODE_TEST);
        assertThat(domainException.getMessage()).isEqualTo(MESSAGE_TEST);

    }

}