package io.exam.match.auth.global.jwt.filter;

import io.exam.match.auth.global.jwt.constants.JWTConstants;
import io.exam.match.auth.global.jwt.provider.TokenProvider;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JWTFilter extends GenericFilter {

    private final TokenProvider tokenProvider;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String token = authorizationHeader(httpServletRequest);

        if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private String authorizationHeader(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader(JWTConstants.AUTHORIZATION_HEADER);

        if (StringUtils.hasText(authorization) && authorization.startsWith(JWTConstants.BEARER_SPACE_PREFIX)) {
            return authorization.substring(7);
        }

        return null;
    }
}
