package io.exam.match.auth.domain.member.persist.domain.dto.request;

import io.exam.match.auth.domain.member.persist.domain.enums.RoleType;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MemberModifyRequest {

    private Long memberId;
    private String password;
    private String username;
    private String nickname;
    private LocalDate birth;
    private RoleType roleType;

    public void exchangePassword(final String encoded) {
        this.password = encoded;
    }
}
