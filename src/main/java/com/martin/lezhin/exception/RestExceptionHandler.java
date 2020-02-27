package com.martin.lezhin.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity handler(Exception e) {
        log.error("예기치 못한 에러 발생 !! ", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("알수 없는 에러가 발생했습니다. 계속 발생할 경우 관리팀에 문의해주세요.");
    }

    @ExceptionHandler(HomeworkException.class)
    public ResponseEntity handler(HomeworkException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(e.getErrorMessage());
    }
}
