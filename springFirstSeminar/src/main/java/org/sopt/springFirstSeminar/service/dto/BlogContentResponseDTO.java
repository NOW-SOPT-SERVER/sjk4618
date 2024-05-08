package org.sopt.springFirstSeminar.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import org.sopt.springFirstSeminar.domain.Blog;
import org.sopt.springFirstSeminar.domain.Post;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record BlogContentResponseDTO(

        @NotBlank
        String name,

        @NotBlank
        String content,

        @NotBlank
        String createdAt
) {
    public static BlogContentResponseDTO of(Post post, String createdAt) {
        return BlogContentResponseDTO.builder()
                .name(post.getName())
                .content(post.getContent())
                .createdAt(createdAt)
                .build();
    }
}
