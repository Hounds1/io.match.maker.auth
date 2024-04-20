package io.exam.match.auth.domain.member.application.service;

import io.exam.match.auth.domain.member.application.dto.request.MemberJoinRequest;
import io.exam.match.auth.domain.member.application.dto.response.SimpleJoinResponse;
import io.exam.match.auth.domain.member.persist.domain.Member;

public interface MemberService {

    SimpleJoinResponse join(final Member member);
}
