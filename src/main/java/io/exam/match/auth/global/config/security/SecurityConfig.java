package io.exam.match.auth.global.config.security;

import io.exam.match.auth.global.config.constants.GlobalConfigurationConstants;
import io.exam.match.auth.global.jwt.filter.JWTAccessDeniedHandler;
import io.exam.match.auth.global.jwt.filter.JWTAuthenticationEntryPoint;
import io.exam.match.auth.global.jwt.filter.JWTFilter;
import io.exam.match.auth.global.jwt.provider.TokenProvider;
import jakarta.ws.rs.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.web.cors.CorsConfiguration.ALL;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JWTAccessDeniedHandler jwtAccessDeniedHandler;
    private final JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin(AbstractHttpConfigurer::disable);

        httpSecurity.sessionManagement(sessionManagementConfigurer -> {
            sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        httpSecurity.exceptionHandling(exceptionHandlingConfigurer -> {
            exceptionHandlingConfigurer.accessDeniedHandler(jwtAccessDeniedHandler)
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint);
        });

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.cors(corsCOnfigurer -> {
            corsCOnfigurer.configurationSource(corsConfigurationSource());
        });

        httpSecurity.headers(headersConfigurer -> {
            headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin);
        });

        httpSecurity.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.OPTIONS).permitAll()
                    .requestMatchers(GlobalConfigurationConstants.PUBLIC_URL_PREFIX).permitAll()
                    .anyRequest().authenticated();
        });

        httpSecurity.addFilterBefore(new JWTFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern(ALL);
        configuration.addAllowedHeader(ALL);
        configuration.addAllowedMethod(ALL);
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
