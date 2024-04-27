package io.exam.match.auth.domain.member.application.service;

import io.exam.match.auth.domain.member.persist.domain.dto.request.MemberModifyRequest;
import io.exam.match.auth.domain.member.persist.domain.dto.response.MemberDetailResponse;
import io.exam.match.auth.domain.member.persist.domain.dto.response.SimpleJoinResponse;
import io.exam.match.auth.domain.member.persist.domain.Member;

public interface MemberService {

    SimpleJoinResponse join(final Member member);

    MemberDetailResponse modify(final MemberModifyRequest modifyRequest);

    void delete(final Long memberId);
}
