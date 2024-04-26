package io.exam.match.auth.domain.member.persist.domain.dto.request;

import io.exam.match.auth.domain.member.persist.domain.Member;
import io.exam.match.auth.domain.member.persist.domain.enums.RoleType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberJoinRequest {

    private String loginId;

    private String password;

    private String nickname;

    private String username;

    private LocalDate birth;

    private RoleType roleType;


    public Member toEntity() {
        return Member.builder()
                .loginId(loginId)
                .password(password)
                .nickname(nickname)
                .username(username)
                .birth(birth)
                .roleType(roleType)
                .build();
    }
}
