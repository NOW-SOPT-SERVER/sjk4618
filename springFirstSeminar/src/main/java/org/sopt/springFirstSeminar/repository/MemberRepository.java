package org.sopt.springFirstSeminar.repository;

import org.sopt.springFirstSeminar.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {



}
