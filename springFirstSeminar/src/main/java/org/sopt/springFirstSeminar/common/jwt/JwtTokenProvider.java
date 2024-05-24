package org.sopt.springFirstSeminar.common.jwt;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.common.jwt.dto.Token;
import org.sopt.springFirstSeminar.common.jwt.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final JwtTokenGenerator jwtTokenGenerator;

    public Token issueTokens(final Long userId) {
        return Token.of(
                jwtTokenGenerator.generateToken(userId, true),
                jwtTokenGenerator.generateToken(userId, false));
    }

    public Long getUserFromJwt(String token) {
        Claims claims = getBody(token);
        return Long.valueOf(claims.get(USER_ID).toString());
    }




}
