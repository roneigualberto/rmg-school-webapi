package com.gualberto.ronei.rmgschoolapi.application.rest.handle;


import com.gualberto.ronei.rmgschoolapi.domain.shared.exception.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public final ResponseEntity<ApiResponse> handleDomainException(DomainException ex, WebRequest request) {

        ApiResponse response = ApiResponse.builder()
                .message(ex.getMessage())
                .code(ex.getCode())
                .build();

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);

    }

}
