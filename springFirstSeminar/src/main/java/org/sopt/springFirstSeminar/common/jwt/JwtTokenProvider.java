package org.sopt.springFirstSeminar.common.jwt;

import io.jsonwebtoken.JwtParser;
import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.common.jwt.dto.Token;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtTokenProvider {

    private final JwtTokenGenerator jwtTokenGenerator;

    public Token issueTokens(final Long userId) {
        return Token.of(
                jwtTokenGenerator.generateToken(userId, true),
                jwtTokenGenerator.generateToken(userId, false));
    }

    public Long getSubject(String accessToken) {
        JwtParser jwtParser = jwtTokenGenerator.getJwtParser();
        return Long.valueOf(jwtParser.parseClaimsJws(accessToken)
                .getBody()
                .getSubject());
    }
}
