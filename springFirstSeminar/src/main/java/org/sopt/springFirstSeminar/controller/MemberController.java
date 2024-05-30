package org.sopt.springFirstSeminar.controller;


import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.common.ApiResponseUtil;
import org.sopt.springFirstSeminar.common.BaseResponse;
import org.sopt.springFirstSeminar.common.dto.SuccessMessage;
import org.sopt.springFirstSeminar.common.jwt.auth.MemberId;
import org.sopt.springFirstSeminar.common.jwt.dto.TokenAndUserIdResponse;
import org.sopt.springFirstSeminar.service.MemberService;
import org.sopt.springFirstSeminar.service.dto.MemberCreateDTO;
import org.sopt.springFirstSeminar.service.dto.MemberFindDTO;
import org.sopt.springFirstSeminar.service.dto.MemberDataDTO;
import org.sopt.springFirstSeminar.service.dto.ReissueRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.sopt.springFirstSeminar.common.Constant.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("signup")
    public ResponseEntity<BaseResponse<?>> postMember(@RequestBody MemberCreateDTO memberCreate)
    {
        final TokenAndUserIdResponse memberJoinResponse = memberService.createMember(memberCreate);

        return ApiResponseUtil.success(SuccessMessage.MEMBER_CREATE_SUCCESS, memberJoinResponse);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<?>> findMemberById(@MemberId final Long memberId) {

        final MemberFindDTO memberFindDTO = memberService.findMemberById(memberId);

        return ApiResponseUtil.success(SuccessMessage.MEMBER_FIND_SUCCESS, memberFindDTO);
    }

    @PostMapping("reissue")
    public ResponseEntity<BaseResponse<?>> reissue(@RequestHeader(AUTHORIZATION) final String refreshToken,
                                                   @RequestBody final ReissueRequest reissueRequest) {

        final TokenAndUserIdResponse reissueTokenResponse = memberService.reissue(refreshToken, reissueRequest);

        return ApiResponseUtil.success(SuccessMessage.TOKEN_REISSUE_SUCCESS, reissueTokenResponse);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMemberById(@PathVariable final Long memberId) {
        memberService.deleteMemberById(memberId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberDataDTO>> getAllMemberList() {
        return ResponseEntity.ok(memberService.getAllMemberList());
    }
}
