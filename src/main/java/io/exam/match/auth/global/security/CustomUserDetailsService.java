package io.exam.match.auth.global.security;

import io.exam.match.auth.domain.member.persist.repository.MemberRepository;
import io.exam.match.auth.global.security.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
         return memberRepository.findDetailsByLoginId(loginId)
                 .orElseThrow(() -> new RuntimeException("Member Not Found"));
    }
}
