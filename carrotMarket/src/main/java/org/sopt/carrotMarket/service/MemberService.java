package org.sopt.carrotMarket.service;


import lombok.RequiredArgsConstructor;
import org.sopt.carrotMarket.domain.Member;
import org.sopt.carrotMarket.repository.MemberRepository;
import org.sopt.carrotMarket.service.dto.RegisterMemberRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void registerMember(RegisterMemberRequestDTO member) {
        Member newMember = Member.register(member.name());
        memberRepository.save(newMember);
    }
}
