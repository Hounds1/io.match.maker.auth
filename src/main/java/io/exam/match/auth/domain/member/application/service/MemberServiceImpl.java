package io.exam.match.auth.domain.member.application.service;

import io.exam.match.auth.domain.member.exception.DuplicatedLoginIdException;
import io.exam.match.auth.domain.member.exception.DuplicatedNicknameException;
import io.exam.match.auth.domain.member.exception.MemberNotFoundException;
import io.exam.match.auth.domain.member.persist.domain.dto.request.MemberModifyRequest;
import io.exam.match.auth.domain.member.persist.domain.dto.response.MemberDetailResponse;
import io.exam.match.auth.domain.member.persist.domain.dto.response.SimpleJoinResponse;
import io.exam.match.auth.domain.member.persist.domain.Member;
import io.exam.match.auth.domain.member.persist.repository.MemberRepository;
import io.exam.match.auth.global.error.exception.GlobalExceptionTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SimpleJoinResponse join(final Member member) {
        if (memberRepository.existsMemberByLoginId(member.getLoginId())) {
            throw new DuplicatedLoginIdException(GlobalExceptionTypes.DUPLICATED_LOGIN_ID);
        }

        member.exchangeEncodedPassword(encodePassword(member.getPassword()));

        Member savedMember = memberRepository.save(member);

        return new SimpleJoinResponse(savedMember.getLoginId(), savedMember.getNickname());
    }

    @Override
    public MemberDetailResponse modify(final MemberModifyRequest modifyRequest) {
        if (memberRepository.existsMemberByNickname(modifyRequest.getNickname())) {
            throw new DuplicatedNicknameException(GlobalExceptionTypes.DUPLICATED_NICKNAME);
        }

        Member foundMember = memberRepository.findById(modifyRequest.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException(GlobalExceptionTypes.MEMBER_NOT_FOUND));

        if (StringUtils.hasText(modifyRequest.getPassword())) {
            modifyRequest.exchangePassword(encodePassword(modifyRequest.getPassword()));
        }

        foundMember.modify(modifyRequest);

        memberRepository.save(foundMember);

        return MemberDetailResponse.of(foundMember);
    }

    @Override
    public void delete(final Long memberId) {
        memberRepository.deleteById(memberId);
    }

    private String encodePassword(final String password) {
        return passwordEncoder.encode(password);
    }
}