package org.sopt.springFirstSeminar.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.common.dto.ErrorMessage;
import org.sopt.springFirstSeminar.domain.Blog;
import org.sopt.springFirstSeminar.domain.Member;
import org.sopt.springFirstSeminar.exception.NotFoundException;
import org.sopt.springFirstSeminar.repository.BlogRepository;
import org.sopt.springFirstSeminar.service.dto.BlogCreateRequest;
import org.sopt.springFirstSeminar.service.dto.BlogTitleUpdateRequest;
import org.sopt.springFirstSeminar.service.dto.MemberFindDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final MemberService memberService;

    public String create(Long memberId, BlogCreateRequest blogCreateRequest) {
        Member member = memberService.findById(memberId);
        Blog blog = blogRepository.save(Blog.create(member, blogCreateRequest));
        return blog.getId().toString();
    }

    @Transactional //이거를 적거나 이 메서드 아래에 blogRepository.save()를 해도 됨.
    public void updateTitle(Long blogId, BlogTitleUpdateRequest blogTitleUpdateRequest) {
        Blog blog = findBlogById(blogId);
        blog.updateTitle(blogTitleUpdateRequest.title());
    }

    public Blog findBlogById(Long blogId) {
        return blogRepository.findById(blogId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.BLOG_NOT_FOUND)
        );
    }

}
