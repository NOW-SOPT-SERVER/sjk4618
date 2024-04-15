package org.sopt.springFirstSeminar.controller;


import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.service.MemberService;
import org.sopt.springFirstSeminar.service.dto.MemberCreateDTO;
import org.sopt.springFirstSeminar.service.dto.MemberFindDTO;
import org.sopt.springFirstSeminar.service.dto.MemberDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity createMember(@RequestBody MemberCreateDTO memberCreate) {
        return ResponseEntity.created(URI.create(memberService.createMember(memberCreate)))
                .build();
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberFindDTO> findMemberById(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.findMemberById(memberId));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMemberById(@PathVariable Long memberId) {
        memberService.deleteMemberById(memberId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/memberlist")
    public ResponseEntity<List<MemberDataDTO>> getAllMemberList() {
        return ResponseEntity.ok(memberService.getAllMemberList());
    }

}
