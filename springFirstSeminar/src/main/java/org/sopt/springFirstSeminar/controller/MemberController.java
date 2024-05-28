package org.sopt.springFirstSeminar.controller;


import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.sopt.springFirstSeminar.common.ApiResponseUtil;
import org.sopt.springFirstSeminar.common.BaseResponse;
import org.sopt.springFirstSeminar.common.Constant;
import org.sopt.springFirstSeminar.common.dto.SuccessMessage;
import org.sopt.springFirstSeminar.common.jwt.auth.MemberId;
import org.sopt.springFirstSeminar.common.jwt.dto.TokenResponse;
import org.sopt.springFirstSeminar.service.MemberService;
import org.sopt.springFirstSeminar.service.dto.MemberCreateDTO;
import org.sopt.springFirstSeminar.service.dto.MemberFindDTO;
import org.sopt.springFirstSeminar.service.dto.MemberDataDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<BaseResponse<?>> postMember(@RequestBody MemberCreateDTO memberCreate)
    {
        final TokenResponse memberJoinResponse = memberService.createMember(memberCreate);

        return ApiResponseUtil.success(SuccessMessage.MEMBER_CREATE_SUCCESS, memberJoinResponse);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<?>> findMemberById(@MemberId final Long memberId) {

        final MemberFindDTO memberFindDTO = memberService.findMemberById(memberId);

        return ApiResponseUtil.success(SuccessMessage.MEMBER_FIND_SUCCESS, memberFindDTO);
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
