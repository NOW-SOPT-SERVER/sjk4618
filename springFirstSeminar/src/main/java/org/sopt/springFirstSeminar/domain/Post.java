package org.sopt.springFirstSeminar.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.springFirstSeminar.service.dto.BlogContentRequestDTO;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blog;

    @NotBlank
    private String name;

    @NotBlank
    private String content;

    private Post(Blog blog, String name, String content) {
        this.blog = blog;
        this.name = name;
        this.content = content;
    }

    public static Post create(Blog blog, BlogContentRequestDTO blogContentRequestDTO) {
        return new Post(blog, blogContentRequestDTO.name(), blogContentRequestDTO.content());
    }
}
