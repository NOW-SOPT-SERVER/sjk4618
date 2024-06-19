package org.sopt.carrotMarket.service.dto;

import lombok.Builder;
import org.springframework.http.ResponseEntity;

@Builder
public record RegisterMemberResponseDTO(
        Long memberId
) {
    public static RegisterMemberResponseDTO of(Long memberId) {
        return RegisterMemberResponseDTO.builder()
                .memberId(memberId)
                .build();
    }
}
