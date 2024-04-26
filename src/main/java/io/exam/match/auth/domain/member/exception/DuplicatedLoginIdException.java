package io.exam.match.auth.domain.member.exception;

import io.exam.match.auth.global.error.exception.GlobalExceptionTypes;
import io.exam.match.auth.global.error.exception.MatcherGlobalException;

public class DuplicatedLoginIdException extends MatcherGlobalException {
    public DuplicatedLoginIdException(GlobalExceptionTypes globalExceptionTypes) {
        super(globalExceptionTypes);
    }
}
