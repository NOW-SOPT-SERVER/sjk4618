package org.sopt.springFirstSeminar.common.jwt.auth.redis.repository;

import org.sopt.springFirstSeminar.common.jwt.auth.redis.Token;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token, String> {
    Optional<Token> findByRefreshToken(final String refreshToken);
    Optional<Token> findById(final Long id);
}
