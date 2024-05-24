package org.sopt.springFirstSeminar.controller;


import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.common.jwt.dto.UserJoinResponse;
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
    public ResponseEntity<UserJoinResponse> postMember(
            @RequestBody MemberCreateDTO memberCreate
    ) {
        UserJoinResponse userJoinResponse = memberService.createMember(memberCreate);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", userJoinResponse.userId())
                .body(
                        userJoinResponse
                );
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberFindDTO> findMemberById(@PathVariable final Long memberId) {
        return ResponseEntity.ok(memberService.findMemberById(memberId));
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
