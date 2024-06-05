package org.sopt.springFirstSeminar.common.jwt.auth.redis.repository;

import org.sopt.springFirstSeminar.common.jwt.auth.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
}
