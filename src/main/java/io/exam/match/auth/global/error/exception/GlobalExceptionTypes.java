package io.exam.match.auth.global.error.exception;

import lombok.Getter;

@Getter
public enum GlobalExceptionTypes {

    /**
     * Global
     */
    BAD_REQUEST(400, "BAD REQUEST", "Invalid Request has been detected."),

    /**
     * Member (1)
     */
    MEMBER_NOT_FOUND(400, "1001", "An expected member of id has not been founded."),
    DUPLICATED_LOGIN_ID(400, "1002", "Duplicated Login ID has been detected."),
    DUPLICATED_NICKNAME(400, "1003", "Duplicated nickname has been detected."),
    MEMBER_MODIFY_FAILED(500, "1004", "Tried to modify a member, but the modification process failed due to an unknown reason."),
    MEMBER_DELETE_FAILED(500, "1005", "Tried to delete a member, but the deletion process failed. The member may not exist."),

    /**
     * Auth (8)
     */
    PASSWORD_MISMATCHED(403, "8000", "Access Denied to account."),

    /**
     * JWT (9)
     */
    TOKEN_NOT_FOUND(400, "9001", "Tried to validate token, but validation failed due to an unknown reason."),
    INVALID_JWT_TOKEN(403, "9002", "Invalid JWT Token has been detected.");

    private final int code;
    private final String status;
    private final String message;

    GlobalExceptionTypes(int code, String status, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
