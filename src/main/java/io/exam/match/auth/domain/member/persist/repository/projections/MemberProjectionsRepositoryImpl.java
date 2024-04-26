package io.exam.match.auth.domain.member.persist.repository.projections;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.exam.match.auth.domain.member.persist.domain.QMember;
import io.exam.match.auth.global.security.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberProjectionsRepositoryImpl implements MemberProjectionsRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<CustomUserDetails> findDetailsByLoginId(final String loginId) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .select(Projections.constructor(CustomUserDetails.class,
                                QMember.member.id,
                                QMember.member.loginId,
                                QMember.member.password,
                                QMember.member.username))
                        .from(QMember.member)
                        .where(QMember.member.loginId.eq(loginId))
                        .fetchOne()
        );
    }
}
