package org.sopt.carrotMarket.controller;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.sopt.carrotMarket.common.ApiResponseUtil;
import org.sopt.carrotMarket.common.BaseResponse;
import org.sopt.carrotMarket.common.dto.SuccessMessage;
import org.sopt.carrotMarket.domain.Member;
import org.sopt.carrotMarket.service.MemberService;
import org.sopt.carrotMarket.service.dto.RegisterMemberRequestDTO;
import org.sopt.carrotMarket.service.dto.RegisterMemberResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.path}")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<BaseResponse<?>> createMember(@RequestBody RegisterMemberRequestDTO member) {
        RegisterMemberResponseDTO registerMemberResponseDTO = memberService.registerMember(member);
        return ApiResponseUtil.success(SuccessMessage.MEMBER_REGISTER_SUCCESS, registerMemberResponseDTO);
    }
}
