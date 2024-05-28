package org.sopt.springFirstSeminar.common.jwt.auth;


import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "", timeToLive = 60 * 60 * 24 * 1000L)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder(access = AccessLevel.PRIVATE)
public class RefreshToken {

    @Id
    private Long id;

    @Indexed
    private String refreshToken;

    public static RefreshToken of(final Long memberId, final String refreshToken) {
        return RefreshToken.builder()
                .id(memberId)
                .refreshToken(refreshToken)
                .build();
    }

    public void updateRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
