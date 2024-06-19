package org.sopt.carrotMarket.service.dto;

import lombok.Builder;
import org.sopt.carrotMarket.domain.Item;

@Builder
public record RegisterItemResponseDTO(
        Long itemId
) {
    public static RegisterItemResponseDTO of(Long itemId) {
        return RegisterItemResponseDTO.builder()
                .itemId(itemId)
                .build();
    }
}
