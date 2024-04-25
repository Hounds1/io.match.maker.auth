package io.exam.match.auth.domain.member.persist.domain.enums;

import lombok.Getter;

@Getter
public enum RoleType {
    ADMIN("ADMIN"),
    USER("USER");

    private final String type;

    RoleType(String type) {
        this.type = type;
    }
}
