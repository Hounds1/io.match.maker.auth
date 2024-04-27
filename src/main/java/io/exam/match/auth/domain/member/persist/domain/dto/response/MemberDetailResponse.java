package io.exam.match.auth.domain.member.persist.domain.dto.response;

import io.exam.match.auth.domain.member.persist.domain.Member;
import io.exam.match.auth.domain.member.persist.domain.enums.RoleType;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MemberDetailResponse {

    private Long id;
    private String loginId;
    private String nickname;
    private String username;
    private LocalDate birth;
    private RoleType roleType;

    public static MemberDetailResponse of(final Member member) {
        return new MemberDetailResponse(member.getId(),
                member.getLoginId(),
                member.getNickname(),
                member.getUsername(),
                member.getBirth(),
                member.getRoleType());
    }
}
