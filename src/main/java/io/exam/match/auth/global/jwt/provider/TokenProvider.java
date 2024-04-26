package io.exam.match.auth.global.jwt.provider;

import io.exam.match.auth.global.jwt.constants.JWTConstants;
import io.exam.match.auth.global.jwt.dto.TokenDTO;
import io.exam.match.auth.global.security.CustomUserDetailsService;
import io.exam.match.auth.global.security.principal.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenProvider implements InitializingBean {

    @Value("${jwt.expression.secret}")
    private String secret;
    @Value("${jwt.expression.access-token-expire}")
    private Long accessTokenExpire;
    @Value("${jwt.expression.refresh-token-expire}")
    private Long refreshTokenExpire;

    private final CustomUserDetailsService customUserDetailsService;

    private Key key;

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDTO generateToken(String loginId, Authentication authentication) {
        long now = new Date().getTime();

        String accessToken = Jwts.builder()
                .claim("loginId", loginId)
                .setExpiration(new Date(now + accessTokenExpire * 1000))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        String refreshToken = Jwts.builder()
                .claim("loginId", loginId)
                .setExpiration(new Date(now + refreshTokenExpire * 1000))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return TokenDTO.of(accessToken, refreshToken, JWTConstants.BEARER_PREFIX);
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        CustomUserDetails principal = customUserDetailsService.loadUserByUsername((String) claims.get("loginId"));

        return new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.error("Invalid JWT Token has been detected.");
        }catch (ExpiredJwtException e) {
            log.error("Expired JWT Token has been detected.");
        }catch (UnsupportedJwtException e) {
            log.error("Unsupported Type JWT Token has been detected.");
        }catch (IllegalArgumentException e) {
            log.error("Token has a problem.");
        }
        return false;
    }
}
