package io.exam.match.auth.domain.member.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleJoinResponse {

    private String loginId;

    private String nickname;

    public SimpleJoinResponse(String loginId, String nickname) {
        this.loginId = loginId;
        this.nickname = nickname;
    }
}
