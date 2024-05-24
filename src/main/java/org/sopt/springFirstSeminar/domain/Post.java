package org.sopt.springFirstSeminar.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder
    private Post(Blog blog, String name, String content) {
        this.blog = blog;
        this.name = name;
        this.content = content;
    }

//    public static Post create(Blog blog, BlogContentRequestDTO blogContentRequestDTO) {
//        return new Post(blog, blogContentRequestDTO.name(), blogContentRequestDTO.content());
//    }
}
