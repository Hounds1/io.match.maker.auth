package io.exam.match.auth.global.error.exception;

import lombok.Getter;

@Getter
public class MatcherGlobalException extends RuntimeException {

    private final GlobalExceptionTypes globalExceptionTypes;

    public MatcherGlobalException(final GlobalExceptionTypes globalExceptionTypes) {
        super(globalExceptionTypes.getMessage());
        this.globalExceptionTypes = globalExceptionTypes;
    }
}
