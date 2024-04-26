package io.exam.match.auth.domain.member.exception;

import io.exam.match.auth.global.error.exception.GlobalExceptionTypes;
import io.exam.match.auth.global.error.exception.MatcherGlobalException;

public class DuplicatedNicknameException extends MatcherGlobalException {
    public DuplicatedNicknameException(GlobalExceptionTypes globalExceptionTypes) {
        super(globalExceptionTypes);
    }
}
