package io.exam.match.auth.global.jwt.dto;

import io.exam.match.auth.global.jwt.constants.JWTConstants;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenDTO {

    private String accessToken;
    private String refreshToken;
    private String type;

    public static TokenDTO of(String accessToken, String refreshToken, String type) {
        return new TokenDTO(accessToken, refreshToken, type);
    }
}
