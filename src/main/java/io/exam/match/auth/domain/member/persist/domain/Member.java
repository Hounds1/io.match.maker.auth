package io.exam.match.auth.domain.member.persist.domain;

import io.exam.match.auth.domain.member.persist.domain.enums.RoleType;
import io.exam.match.auth.global.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}