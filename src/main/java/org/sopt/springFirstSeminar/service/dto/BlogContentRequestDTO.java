package org.sopt.springFirstSeminar.service.dto;

import jakarta.validation.constraints.Size;

public record BlogContentRequestDTO(
        String name,

        @Size(max = 20, message = "블로그 글이 최대 글자 수(20)를 초과했습니다")
        String content
) {
}
