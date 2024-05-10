package io.exam.match.auth.domain.auth.exception;

import io.exam.match.auth.global.error.exception.GlobalExceptionTypes;
import io.exam.match.auth.global.error.exception.MatcherGlobalException;

public class PasswordMismatchException extends MatcherGlobalException {
    public PasswordMismatchException(GlobalExceptionTypes globalExceptionTypes) {
        super(globalExceptionTypes);
    }
}
