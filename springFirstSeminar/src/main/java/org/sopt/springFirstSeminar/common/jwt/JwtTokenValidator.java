package org.sopt.springFirstSeminar.common.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.common.dto.ErrorMessage;
import org.sopt.springFirstSeminar.exception.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class JwtTokenValidator {

    private final JwtTokenGenerator jwtTokenGenerator;

    public void validateAccessToken(String accessToken) {
        try {
            parseToken(accessToken);
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException(ErrorMessage.EXPIRED_ACCESS_TOKEN);
        } catch (MalformedJwtException ex) {
            throw new UnauthorizedException(ErrorMessage.INVALID_ACCESS_TOKEN);
        } catch (UnsupportedJwtException ex) {
            throw new UnauthorizedException(ErrorMessage.UNSUPPORTED_ACCESS_TOKEN);
        } catch (IllegalArgumentException ex) {
            throw new UnauthorizedException(ErrorMessage.EMPTY_ACCESS_TOKEN);
        } catch (SignatureException ex) {
            throw new UnauthorizedException(ErrorMessage.JWT_SIGNATURE_EXCEPTION);
        }
    }

    public void validateRefreshToken(String refreshToken) {
        try {
            parseToken(refreshToken);
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException(ErrorMessage.EXPIRED_REFRESH_TOKEN);
        } catch (Exception e) {
            throw new UnauthorizedException(ErrorMessage.INVALID_REFRESH_TOKEN_VALUE);
        }
    }

    public void equalsRefreshToken(String refreshToken, String storedRefreshToken) {
        if (!refreshToken.equals(storedRefreshToken)) {
            throw new UnauthorizedException(ErrorMessage.MISMATCH_REFRESH_TOKEN);
        }
    }

    private void parseToken(String token) {
        JwtParser jwtParser = jwtTokenGenerator.getJwtParser();
        jwtParser.parseClaimsJws(token);
    }
}
