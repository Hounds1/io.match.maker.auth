package io.exam.match.auth.domain.auth.application.service;

import io.exam.match.auth.domain.auth.domain.Response.AccessToken;
import io.exam.match.auth.domain.auth.domain.request.AuthRequest;
import io.exam.match.auth.global.error.exception.GlobalExceptionTypes;
import io.exam.match.auth.global.jwt.dto.TokenDTO;
import io.exam.match.auth.global.jwt.exception.InvalidJWTTokenException;
import io.exam.match.auth.global.jwt.provider.TokenProvider;
import io.exam.match.auth.global.security.CustomUserDetailsService;
import io.exam.match.auth.global.security.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public TokenDTO auth(final AuthRequest authRequest) {
        CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequest.getLoginId());

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authRequest.getLoginId(), authRequest.getPassword());

        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(token);

        return tokenProvider.generateToken(userDetails.getLoginId(), authenticate);
    }

    @Override
    public AccessToken reissue(final String accessToken, final String refreshToken) {
        if (!StringUtils.hasText(refreshToken) || !tokenProvider.validateToken(refreshToken)) {
            throw new InvalidJWTTokenException(GlobalExceptionTypes.INVALID_JWT_TOKEN);
        }

        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();

        return AccessToken.of(tokenProvider.generateToken(principal.getLoginId(), authentication));
    }
}
