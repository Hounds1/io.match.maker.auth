package io.exam.match.auth.global.error.advice;

import io.exam.match.auth.global.error.exception.GlobalExceptionResponse;
import io.exam.match.auth.global.error.exception.GlobalExceptionTypes;
import io.exam.match.auth.global.error.exception.MatcherGlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(MatcherGlobalException.class)
    public ResponseEntity<GlobalExceptionResponse> handleMatcherGlobalException(MatcherGlobalException e) {
        GlobalExceptionTypes globalExceptionTypes = e.getGlobalExceptionTypes();

        return new ResponseEntity<>(GlobalExceptionResponse.of(globalExceptionTypes), HttpStatus.valueOf(globalExceptionTypes.getCode()));
    }
}
