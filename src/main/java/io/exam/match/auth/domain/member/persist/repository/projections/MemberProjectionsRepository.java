package io.exam.match.auth.domain.member.persist.repository.projections;

import io.exam.match.auth.global.security.principal.CustomUserDetails;

import java.util.Optional;

public interface MemberProjectionsRepository {

    Optional<CustomUserDetails> findDetailsByLoginId(final String loginId);
}
