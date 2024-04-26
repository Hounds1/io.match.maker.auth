package io.exam.match.auth.global.error.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GlobalExceptionResponse {

    private int code;
    private String status;
    private String message;

    public GlobalExceptionResponse(final GlobalExceptionTypes globalExceptionTypes) {
        this.code = globalExceptionTypes.getCode();
        this.status = globalExceptionTypes.getStatus();
        this.message = globalExceptionTypes.getMessage();
    }

    public static GlobalExceptionResponse of(final GlobalExceptionTypes globalExceptionTypes) {
        return new GlobalExceptionResponse(globalExceptionTypes);
    }
}
