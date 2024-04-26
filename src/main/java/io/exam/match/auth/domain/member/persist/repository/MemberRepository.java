package io.exam.match.auth.domain.member.persist.repository;

import io.exam.match.auth.domain.member.persist.domain.Member;
import io.exam.match.auth.domain.member.persist.repository.projections.MemberProjectionsRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberProjectionsRepository {

    boolean existsMemberByLoginId(final String loginId);

    boolean existsMemberByNickname(final String nickname);
}
