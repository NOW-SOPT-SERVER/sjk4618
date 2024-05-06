package org.sopt.springFirstSeminar.service.dto;

import lombok.Builder;
import org.sopt.springFirstSeminar.domain.Post;

public record BlogAllContentResponseDTO(
        Long postId,
        String name,
        String content
) {

    @Builder //빌더패턴
    public BlogAllContentResponseDTO( Long postId, String name, String content
    ) {
        this.postId = postId;
        this.name = name;
        this.content = content;
    }

    public static BlogAllContentResponseDTO of(Post post) {
        return BlogAllContentResponseDTO.builder()
                .postId(post.getId())
                .name(post.getName())
                .content(post.getContent())
                .build();

    }
}
