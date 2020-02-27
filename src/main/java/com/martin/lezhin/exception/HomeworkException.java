package com.martin.lezhin.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HomeworkException extends RuntimeException {
    private String errorMessage;
    private HttpStatus status;

    public HomeworkException(String errorMessage, HttpStatus status) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.status = status;
    }
}
