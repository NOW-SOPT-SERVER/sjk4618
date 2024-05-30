package org.sopt.springFirstSeminar.common.jwt.dto;

public record TokenAndUserIdResponse(
        String accessToken,
        String refreshToken,
        Long userId
) {

    public static TokenAndUserIdResponse of(Token token, Long memberId) {
        return new TokenAndUserIdResponse(token.accessToken(), token.refreshToken(), memberId);
    }
}


