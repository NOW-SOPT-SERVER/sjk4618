package org.sopt.springFirstSeminar.service;

import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.common.dto.ErrorMessage;
import org.sopt.springFirstSeminar.common.dto.ErrorResponse;
import org.sopt.springFirstSeminar.common.dto.SuccessMessage;
import org.sopt.springFirstSeminar.common.dto.SuccessStatusResponse;
import org.sopt.springFirstSeminar.domain.Blog;
import org.sopt.springFirstSeminar.domain.Post;
import org.sopt.springFirstSeminar.exception.NotFoundException;
import org.sopt.springFirstSeminar.repository.BlogRepository;
import org.sopt.springFirstSeminar.repository.PostRepository;
import org.sopt.springFirstSeminar.service.dto.BlogAllContentResponseDTO;
import org.sopt.springFirstSeminar.service.dto.BlogContentRequestDTO;
import org.sopt.springFirstSeminar.service.dto.BlogContentResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;
    private final BlogRepository blogRepository;

    @Transactional
    public String postContent(final Long memberId, final Long blogId, final BlogContentRequestDTO blogContentRequestDTO) {
        memberService.findById(memberId);

        Blog blog = fineBlogById(blogId);

        if(isBlogMemberIdSameWithMemberId(blog, memberId)) {
            Post post = postRepository.save(
                    Post.builder()
                    .blog(blog)
                    .name(blogContentRequestDTO.name())
                    .content(blogContentRequestDTO.content())
                    .build());
            return post.getId().toString();
        } else {
            throw new NotFoundException(ErrorMessage.BLOG_NOT_MATCH_MEMBER); //블로그id와 멤버id가 일치하지 않을 때!
        }
    }

    public boolean isBlogMemberIdSameWithMemberId(final Blog blog, final Long memberId) {
        return blog.getMember().getId().equals(memberId);
    }

    public BlogContentResponseDTO getBlogContent(final Long postId) {
        Post findPost = getPostById(postId);
        String createTimeString = findPost.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return BlogContentResponseDTO.of(findPost, createTimeString);
    }

    public List<BlogAllContentResponseDTO> getBlogAllContent(final Long blogId) {
        Blog blog = fineBlogById(blogId);

        return postRepository.findById(blogId)
                .stream()
                .map(BlogAllContentResponseDTO::of)
                .toList();
    }

    private Post getPostById(final Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.CONTENT_NOT_FOUND)
        );
    }

    private Blog fineBlogById(final Long blogId) {
        return blogRepository.findById(blogId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.BLOG_NOT_FOUND));
    }
}
