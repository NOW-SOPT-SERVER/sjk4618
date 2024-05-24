package org.sopt.springFirstSeminar.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import org.sopt.springFirstSeminar.domain.Post;

@Builder(access = AccessLevel.PRIVATE)
public record BlogContentResponseDTO(

        @NotBlank
        String name,

        @NotBlank
        String content,

        @NotBlank
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
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
