package org.sopt.springFirstSeminar.common.jwt.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.common.Constant;
import org.sopt.springFirstSeminar.common.dto.ErrorMessage;
import org.sopt.springFirstSeminar.common.jwt.JwtTokenProvider;
import org.sopt.springFirstSeminar.common.jwt.JwtTokenValidator;
import org.sopt.springFirstSeminar.common.jwt.UserAuthentication;
import org.sopt.springFirstSeminar.exception.UnauthorizedException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.sopt.springFirstSeminar.common.jwt.UserAuthentication.createUserAuthentication;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenValidator jwtTokenValidator;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String accessToken = getAccessToken(request);
        jwtTokenValidator.validateAccessToken(accessToken);
        doAuthentication(request, jwtTokenProvider.getSubject(accessToken));
        filterChain.doFilter(request, response);
    }

    //userId로 UserAuthentication 객체 생성
    private void doAuthentication(HttpServletRequest request, Long userId) {
        UserAuthentication authentication = createUserAuthentication(userId);
        createAndSetWebAuthenticationDetails(request, authentication);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
    }

    private void createAndSetWebAuthenticationDetails(HttpServletRequest request, UserAuthentication authentication) {
        WebAuthenticationDetailsSource webAuthenticationDetailsSource = new WebAuthenticationDetailsSource();
        WebAuthenticationDetails webAuthenticationDetails = webAuthenticationDetailsSource.buildDetails(request);
        authentication.setDetails(webAuthenticationDetails);
    }

    //토큰 추출
    private String getAccessToken(final HttpServletRequest request) {
        String accessToken = request.getHeader(Constant.AUTHORIZATION);
        if (StringUtils.hasText(accessToken) && accessToken.startsWith(Constant.BEARER)) {
            return accessToken.substring(Constant.BEARER.length());
        }
        throw new UnauthorizedException(ErrorMessage.INVALID_ACCESS_TOKEN);
    }
}
