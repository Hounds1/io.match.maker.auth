package io.exam.match.auth.domain.member.exception;

import io.exam.match.auth.global.error.exception.GlobalExceptionTypes;
import io.exam.match.auth.global.error.exception.MatcherGlobalException;

public class MemberNotFoundException extends MatcherGlobalException {
    public MemberNotFoundException(GlobalExceptionTypes globalExceptionTypes) {
        super(globalExceptionTypes);
    }
}
