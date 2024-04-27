package io.exam.match.auth.domain.member.persist.domain;

import io.exam.match.auth.domain.member.persist.domain.dto.request.MemberModifyRequest;
import io.exam.match.auth.domain.member.persist.domain.enums.RoleType;
import io.exam.match.auth.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Long id;

    private String loginId;

    private String password;

    private String nickname;

    private String username;

    private LocalDate birth;

    private RoleType roleType;

    public void exchangeEncodedPassword(final String password) {
        this.password = password;
    }

    public void modify(MemberModifyRequest request) {
        if (StringUtils.hasText(request.getPassword())) {
            this.password = request.getPassword();
        }

        this.nickname = request.getNickname();
        this.username = request.getUsername();
        this.birth = request.getBirth();
        this.roleType = request.getRoleType();
    }

}