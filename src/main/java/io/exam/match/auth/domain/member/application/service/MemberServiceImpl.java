package io.exam.match.auth.domain.member.application.service;

import io.exam.match.auth.domain.member.persist.domain.dto.response.SimpleJoinResponse;
import io.exam.match.auth.domain.member.persist.domain.Member;
import io.exam.match.auth.domain.member.persist.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SimpleJoinResponse join(final Member member) {
        Member savedMember = memberRepository.save(member);

        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.exchangeEncodedPassword(encodedPassword);

        return new SimpleJoinResponse(savedMember.getLoginId(), savedMember.getNickname());
    }
}
