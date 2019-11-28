package com.github.arkronzxc.temperaturesensors.resourse.exceptions;

import org.springframework.http.HttpStatus;

public class CustomWebException extends RuntimeException {

    private HttpStatus status;

    public CustomWebException(HttpStatus status) {
        super();
        this.status = status;
    }

    public CustomWebException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public CustomWebException(String message, HttpStatus status, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public CustomWebException(HttpStatus status, Throwable cause) {
        super(cause);
        this.status = status;
    }


    public HttpStatus getStatus() {
        return status;
    }
}
