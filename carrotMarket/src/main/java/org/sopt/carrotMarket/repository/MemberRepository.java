package org.sopt.carrotMarket.repository;

import org.sopt.carrotMarket.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
