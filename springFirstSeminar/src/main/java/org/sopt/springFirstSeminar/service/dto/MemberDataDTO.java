package org.sopt.springFirstSeminar.service.dto;

import org.sopt.springFirstSeminar.domain.Member;
import org.sopt.springFirstSeminar.domain.Part;

public record MemberDataDTO(
        Long id,
        String name,
        Part part,
        int age
) {
    public static MemberDataDTO of(Member member) {
        return new MemberDataDTO(member.getId(),member.getName(), member.getPart(), member.getAge());
    }
}

