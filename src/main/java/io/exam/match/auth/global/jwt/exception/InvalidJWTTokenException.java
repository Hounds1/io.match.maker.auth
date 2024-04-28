package io.exam.match.auth.global.jwt.exception;

import io.exam.match.auth.global.error.exception.GlobalExceptionTypes;
import io.exam.match.auth.global.error.exception.MatcherGlobalException;

public class InvalidJWTTokenException extends MatcherGlobalException {
    public InvalidJWTTokenException(GlobalExceptionTypes globalExceptionTypes) {
        super(globalExceptionTypes);
    }
}
