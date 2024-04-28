package io.exam.match.auth.domain.auth.application.service;

import io.exam.match.auth.domain.auth.domain.Response.AccessToken;
import io.exam.match.auth.domain.auth.domain.request.AuthRequest;
import io.exam.match.auth.global.jwt.dto.TokenDTO;

public interface AuthService {

    TokenDTO auth(final AuthRequest authRequest);

    AccessToken reissue(final String accessToken, final String refreshToken);
}
