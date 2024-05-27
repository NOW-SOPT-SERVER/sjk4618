package org.sopt.springFirstSeminar.common.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.sopt.springFirstSeminar.common.jwt.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenGenerator {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.access-token-expire-time}")
    private long ACCESS_TOKEN_EXPIRE_TIME;
    @Value("${jwt.refresh-token-expire-time}")
    private long REFRESH_TOKEN_EXPIRE_TIME;

    public String generateToken(final Long userId, boolean isAccessToken) {
        final Date presentDate = new Date();
        final Date expireDate = generateExpireDataByToken(isAccessToken, presentDate);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject(String.valueOf(userId))
                .setIssuedAt(presentDate)
                .setExpiration(expireDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) //여기서 어떤 알고리즘을 사용할 지를 명시적으로 적어주는게 좋음, 안 적어주면 라이브러리 기본 설정에 의존하게됨
                .compact();
    }

    public JwtParser getJwtParser() {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build();
    }

    private Date generateExpireDataByToken(final boolean isAccessToken, Date presentDate) {
       return new Date(presentDate.getTime() + setExpireTimeByToken(isAccessToken));
    }

    //토근에 따라 만료시간 다름
    private long setExpireTimeByToken(final boolean isAccessToken) {
        if (isAccessToken) {
            return ACCESS_TOKEN_EXPIRE_TIME;
        } else {
            return REFRESH_TOKEN_EXPIRE_TIME;
        }
    }

    public SecretKey getSigningKey() {
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getBytes()); //SecretKey 통해 서명 생성
        return Keys.hmacShaKeyFor(encodedKey.getBytes());   //일반적으로 HMAC (Hash-based Message Authentication Code) 알고리즘 사용
    }
}
