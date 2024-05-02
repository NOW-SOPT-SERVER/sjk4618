package org.sopt.springFirstSeminar.service.dto;

public record BlogAllContentResponseDTO(
        Long postId,
        String name,
        String content
) {
}
