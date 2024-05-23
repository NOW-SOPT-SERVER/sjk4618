package org.sopt.springFirstSeminar.service;

import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.common.dto.ErrorMessage;
import org.sopt.springFirstSeminar.common.jwt.JwtTokenProvider;
import org.sopt.springFirstSeminar.common.jwt.UserAuthentication;
import org.sopt.springFirstSeminar.common.jwt.dto.UserJoinResponse;
import org.sopt.springFirstSeminar.domain.Member;
import org.sopt.springFirstSeminar.exception.NotFoundException;
import org.sopt.springFirstSeminar.repository.MemberRepository;
import org.sopt.springFirstSeminar.service.dto.MemberCreateDTO;
import org.sopt.springFirstSeminar.service.dto.MemberFindDTO;
import org.sopt.springFirstSeminar.service.dto.MemberDataDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserJoinResponse createMember(
            MemberCreateDTO memberCreate
    ) {
        Member member = memberRepository.save(
                Member.create(memberCreate.name(), memberCreate.part(), memberCreate.age())
        );
        Long memberId = member.getId();
        String accessToken = jwtTokenProvider.issueAccessToken(
                UserAuthentication.createUserAuthentication(memberId)
        );
        return UserJoinResponse.of(accessToken, memberId.toString());
    }

    public void findById(final Long memberId) {
        findMember(memberId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND));
    }

    public MemberFindDTO findMemberById(final Long memberId) {
        return MemberFindDTO.of(findMember(memberId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND)));
    }

    @Transactional
    public void deleteMemberById(final Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND));
        memberRepository.delete(member);
    }

    public Optional<Member> findMember(final Long memberId) {
        return memberRepository.findById(memberId);
    }

    public List<MemberDataDTO> getAllMemberList() {

        // .stream : 메서드가 반환한 컬렉션을 스트림으로 변환함, 스트림을 사용하면 데이터를 순차적으로 처리함
        // .map : 스트림의 각 요소에 대해 MemberDataDTO 객체로 변환하는 작업을 수행함
        // MemberDataDTO.of(member)는 회원 객체를 MemberDataDTO로 변환하는 정적 메서드임
        //.toList : 스트림을 리스트로 변환
        return memberRepository.findAll().stream().map(member -> MemberDataDTO.of(member)).toList();
    }


}
