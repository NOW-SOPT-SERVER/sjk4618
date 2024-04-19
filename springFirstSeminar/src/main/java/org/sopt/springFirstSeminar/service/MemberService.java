package org.sopt.springFirstSeminar.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.domain.Member;
import org.sopt.springFirstSeminar.repository.MemberRepository;
import org.sopt.springFirstSeminar.service.dto.MemberCreateDTO;
import org.sopt.springFirstSeminar.service.dto.MemberFindDTO;
import org.sopt.springFirstSeminar.service.dto.MemberDataDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional //데이터베이스의 변경사항을 적용하는(있을때) 어노테이션
    public String createMember(final MemberCreateDTO memberCreateDTO) { //final 이유 : 인자의 불변성보장
        Member member = Member.create(memberCreateDTO.name(), memberCreateDTO.part(), memberCreateDTO.age());
        memberRepository.save(member);
        return member.getId().toString();
    }

    public MemberFindDTO findMemberById(Long memberId) {
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

    public List<MemberDataDTO> getAllMemberList() {

        // .stream : 메서드가 반환한 컬렉션을 스트림으로 변환함, 스트림을 사용하면 데이터를 순차적으로 처리함
        // .map : 스트림의 각 요소에 대해 MemberDataDTO 객체로 변환하는 작업을 수행함
        // MemberDataDTO.of(member)는 회원 객체를 MemberDataDTO로 변환하는 정적 메서드임
        //.toList : 스트림을 리스트로 변환
        return memberRepository.findAll().stream().map(member -> MemberDataDTO.of(member)).toList();
    }


}
