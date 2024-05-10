package io.exam.match.auth.domain.auth.domain.request;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class AuthRequest {

    private String loginId;
    private String password;
}
