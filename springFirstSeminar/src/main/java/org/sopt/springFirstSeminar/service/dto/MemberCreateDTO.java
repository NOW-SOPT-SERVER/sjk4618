package org.sopt.springFirstSeminar.service.dto;

import org.sopt.springFirstSeminar.domain.Part;

public record MemberCreateDTO(
        String name,
        Part part,
        int age
) { }
