package org.sopt.springFirstSeminar.service.dto;

import org.sopt.springFirstSeminar.domain.Member;
import org.sopt.springFirstSeminar.domain.Part;

public record MemberListDataDTO(
        Long id,
        String name,
        Part part,
        int age
) {
    public static MemberListDataDTO of(Member member) {
        return new MemberListDataDTO(member.getId(),member.getName(), member.getPart(), member.getAge());
    }
}

