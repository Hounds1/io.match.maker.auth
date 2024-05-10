package io.exam.match.auth.domain.auth.controller;

import io.exam.match.auth.domain.auth.application.service.AuthService;
import io.exam.match.auth.domain.auth.domain.Response.AccessToken;
import io.exam.match.auth.domain.auth.domain.request.AuthRequest;
import io.exam.match.auth.global.jwt.constants.JWTConstants;
import io.exam.match.auth.global.jwt.dto.TokenDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @Value("${jwt.expression.refresh-token-expire}")
    private Integer refreshTokenExpire;

    @PostMapping("/public")
    public ResponseEntity<AccessToken> auth(@RequestBody AuthRequest authRequest, HttpServletResponse httpServletResponse) {
        TokenDTO tokenDTO = authService.auth(authRequest);
        String refreshToken = tokenDTO.getRefreshToken();

        httpServletResponse.addCookie(createCookie("refreshToken",
                refreshToken,
                true,
                true,
                "/",
                refreshTokenExpire));

        return ResponseEntity.status(HttpStatus.CREATED).body(AccessToken.of(tokenDTO));
    }

    @PostMapping("/reissue")
    public ResponseEntity<AccessToken> reissue(HttpServletRequest httpServletRequest, @CookieValue("refreshToken") String refreshToken) {
        String accessToken = httpServletRequest.getHeader(JWTConstants.AUTHORIZATION_HEADER);

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.reissue(accessToken, refreshToken));
    }

    private Cookie createCookie(String key, String value, boolean isHttpOnly, boolean isSecure, String path, int maxAge) {
        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(isHttpOnly);
        cookie.setSecure(isSecure);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        return cookie;
    }

}
