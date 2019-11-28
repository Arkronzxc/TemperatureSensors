package com.github.arkronzxc.temperaturesensors.resourse.exceptions.handlers;

import com.github.arkronzxc.temperaturesensors.resourse.exceptions.CustomWebException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {CustomWebException.class})
    protected ResponseEntity<Object> handleCustomWebException(
            CustomWebException ex, WebRequest request) {
        return handleExceptionInternal(ex, new Message(ex.getStatus().value(), ex.getMessage()),
                new HttpHeaders(), ex.getStatus(), request);
    }

    @ExceptionHandler(value
            = {Exception.class})
    protected ResponseEntity<Object> handleAllException(
            Exception ex, WebRequest request) {
        log.error("Extremely bad Exception: ", ex);
        return handleExceptionInternal(ex, new Message(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Getter
    @AllArgsConstructor
    private static final class Message {
        private int code;
        private String message;
    }
}