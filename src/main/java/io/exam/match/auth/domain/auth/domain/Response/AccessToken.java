package io.exam.match.auth.domain.auth.domain.Response;

import io.exam.match.auth.global.jwt.dto.TokenDTO;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class AccessToken {

    private String accessToken;
    private String type;
    private Long expired;
    private String stereoTimeZone;

    public static AccessToken of(TokenDTO tokenDTO) {
        return AccessToken.builder()
                .accessToken(tokenDTO.getAccessToken())
                .type(tokenDTO.getType())
                .expired(tokenDTO.getExpired())
                .stereoTimeZone(tokenDTO.getStereoTimeZone())
                .build();
    }
}
