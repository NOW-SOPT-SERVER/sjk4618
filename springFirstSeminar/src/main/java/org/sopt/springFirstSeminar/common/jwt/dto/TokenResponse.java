package org.sopt.springFirstSeminar.common.jwt.dto;

public record TokenResponse(
        String accessToken,
        String refreshToken,
        String userId
) {

    public static TokenResponse of(
            String accessToken,
            String refreshToken,
            String userId
    ) {
        return new TokenResponse(accessToken, refreshToken, userId);
    }
}


