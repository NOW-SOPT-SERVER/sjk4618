package org.sopt.springFirstSeminar.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.domain.Member;
import org.sopt.springFirstSeminar.repository.MemberRepository;
import org.sopt.springFirstSeminar.service.dto.MemberCreateDTO;
import org.sopt.springFirstSeminar.service.dto.MemberFindDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional //데이터베이스의 변경사항을 적용하는(있을때) 어노테이션
    public String createMember(
           final MemberCreateDTO memberCreateDTO //final 이유 : 인자의 불변성보장
    ) {
        Member member = Member.create(memberCreateDTO.name(),memberCreateDTO.part(), memberCreateDTO.age());
        memberRepository.save(member);
        return member.getId().toString();
    }

    public MemberFindDTO findMemberById(
            Long memberId
    ) {
        return MemberFindDTO.of(memberRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다.")
        ));
    }

    @Transactional
    public void deleteMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다.")
        );
        memberRepository.delete(member);
    }
}
