package com.gualberto.ronei.rmgschoolapi.application.rest.handle;

import com.gualberto.ronei.rmgschoolapi.domain.shared.exception.DomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(MockitoExtension.class)
class CustomResponseEntityExceptionHandlerTest {


    public static final String CODE_TEST = "my-code";
    public static final String MESSAGE_TEST = "Message";

    private CustomResponseEntityExceptionHandler responseEntityExceptionHandler;

    @Mock
    private WebRequest webRequest;


    @BeforeEach
    void setUp() {
        responseEntityExceptionHandler = new CustomResponseEntityExceptionHandler();
    }

    @Test
    void handleDomainException_shouldReturnApiResponse() {

        DomainException exception = new DomainException(CODE_TEST, MESSAGE_TEST);

        ResponseEntity<ApiResponse> response = responseEntityExceptionHandler.handleDomainException(exception, webRequest);

        ApiResponse body = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);


        assertThat(body).isNotNull();
        assertThat(body.getCode()).isEqualTo(CODE_TEST);
        assertThat(body.getMessage()).isEqualTo(MESSAGE_TEST);


    }

}