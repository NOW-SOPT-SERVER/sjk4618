package org.sopt.springFirstSeminar.service;

import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.common.dto.ErrorMessage;
import org.sopt.springFirstSeminar.common.dto.ErrorResponse;
import org.sopt.springFirstSeminar.common.dto.SuccessMessage;
import org.sopt.springFirstSeminar.common.dto.SuccessStatusResponse;
import org.sopt.springFirstSeminar.domain.Blog;
import org.sopt.springFirstSeminar.domain.Post;
import org.sopt.springFirstSeminar.exception.NotFoundException;
import org.sopt.springFirstSeminar.repository.PostRepository;
import org.sopt.springFirstSeminar.service.dto.BlogContentRequestDTO;
import org.sopt.springFirstSeminar.service.dto.BlogContentResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BlogService blogService;
    private final MemberService memberService;

    @Transactional
    public String postContent(Long memberId, Long blogId, BlogContentRequestDTO blogContentRequestDTO) {
        memberService.findById(memberId);
        Blog blog = blogService.findBlogById(blogId);

        if(blog.getMember().getId().equals(memberId)) {
            Post post = postRepository.save(Post.create(blog, blogContentRequestDTO));
            return post.getId().toString();
        } else {
            throw new NotFoundException(ErrorMessage.BLOG_NOT_MATCH_MEMBER); //블로그id와 멤버id가 일치하지 않을 때!
        }
    }

    public BlogContentResponseDTO getBlogContent(Long postId) {
        Post findPost = getPostById(postId);
        String createTimeString = findPost.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return BlogContentResponseDTO.of(findPost, createTimeString);
    }

    private Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.CONTENT_NOT_FOUND)
        );
    }




}
