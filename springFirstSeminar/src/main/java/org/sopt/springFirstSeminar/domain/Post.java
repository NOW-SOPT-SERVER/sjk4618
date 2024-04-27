package org.sopt.springFirstSeminar.domain;


import jakarta.persistence.*;
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

    private String name;

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
