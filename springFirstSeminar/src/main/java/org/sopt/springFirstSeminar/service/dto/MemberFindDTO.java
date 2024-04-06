package org.sopt.springFirstSeminar.service.dto;


import org.sopt.springFirstSeminar.domain.Member;
import org.sopt.springFirstSeminar.domain.Part;

public record MemberFindDTO(
        String name,
        Part part,
        int age
) {
   public static MemberFindDTO of(Member member) {
       return new MemberFindDTO(member.getName(), member.getPart(), member.getAge());
   }
}
