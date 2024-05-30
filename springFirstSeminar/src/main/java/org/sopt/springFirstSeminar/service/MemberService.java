package org.sopt.springFirstSeminar.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.common.dto.ErrorMessage;
import org.sopt.springFirstSeminar.common.jwt.JwtTokenProvider;
import org.sopt.springFirstSeminar.common.jwt.JwtTokenValidator;
import org.sopt.springFirstSeminar.common.jwt.auth.RefreshToken;
import org.sopt.springFirstSeminar.common.jwt.auth.redis.repository.RefreshTokenRepository;
import org.sopt.springFirstSeminar.common.jwt.dto.Token;
import org.sopt.springFirstSeminar.common.jwt.dto.TokenAndUserIdResponse;
import org.sopt.springFirstSeminar.domain.Member;
import org.sopt.springFirstSeminar.exception.NotFoundException;
import org.sopt.springFirstSeminar.exception.UnauthorizedException;
import org.sopt.springFirstSeminar.repository.MemberRepository;
import org.sopt.springFirstSeminar.service.dto.MemberCreateDTO;
import org.sopt.springFirstSeminar.service.dto.MemberFindDTO;
import org.sopt.springFirstSeminar.service.dto.MemberDataDTO;
import org.sopt.springFirstSeminar.service.dto.ReissueRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenValidator jwtTokenValidator;

    //멤버가입
    @Transactional
    public TokenAndUserIdResponse createMember(MemberCreateDTO memberCreate) {

        Member createdMember = memberRepository.save(
                Member.create(memberCreate.name(), memberCreate.part(), memberCreate.age())
        );
        Long createdMemberId = createdMember.getId();
        Token issuedToken = jwtTokenProvider.issueTokens(createdMemberId);
        updateRefreshToken(issuedToken.refreshToken(), createdMemberId);

        return TokenAndUserIdResponse.of(issuedToken, createdMemberId);
    }

    @Transactional
    public TokenAndUserIdResponse reissue(final String refreshToken, final ReissueRequest reissueRequest) {

        Long memberId = reissueRequest.memberId();
        validateRefreshToken(refreshToken,memberId);
        Member member = findMemberById(memberId);
        Token issueedToken = jwtTokenProvider.issueTokens(memberId);
        updateRefreshToken(issueedToken.refreshToken(), memberId);
        return TokenAndUserIdResponse.of(issueedToken, memberId);
    }

    private void validateRefreshToken(final String refreshToken, final Long memberId) {
        try {
            jwtTokenValidator.validateRefreshToken(refreshToken);
            String storedRefreshToken = getRefreshToken(memberId);
            jwtTokenValidator.equalsRefreshToken(refreshToken, storedRefreshToken);
        } catch (UnauthorizedException e) {
            signOut(memberId);
            throw e;
        }
    }

    private String getRefreshToken(final Long memberId) {
        try {
            return getRefreshTokenFromRedis(memberId);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND);
        }
    }

    private String getRefreshTokenFromRedis(Long userId) {
        RefreshToken storedRefreshToken = refreshTokenRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.REFRESH_TOKEN_NOT_FOUND));
        return storedRefreshToken.getRefreshToken();
    }

    @Transactional
    public void updateRefreshToken(String refreshToken, Long memberId) {
        refreshTokenRepository.save(RefreshToken.of(memberId, refreshToken));
    }

    public void signOut(final Long memberId) {
        Member findMember = findMemberById(memberId);
        deleteRefreshToken(findMember);
    }

    @Transactional
    public void deleteRefreshToken(final Member member) {
        refreshTokenRepository.deleteById(member.getId());
    }

    public Member findMemberById(final Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND));
    }

    @Transactional
    public void deleteMemberById(final Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND));
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
