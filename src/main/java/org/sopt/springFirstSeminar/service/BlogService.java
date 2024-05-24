package org.sopt.springFirstSeminar.service;

import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.common.dto.ErrorMessage;
import org.sopt.springFirstSeminar.domain.Blog;
import org.sopt.springFirstSeminar.domain.Member;
import org.sopt.springFirstSeminar.exception.NotFoundException;
import org.sopt.springFirstSeminar.external.S3Service;
import org.sopt.springFirstSeminar.repository.BlogRepository;
import org.sopt.springFirstSeminar.repository.MemberRepository;
import org.sopt.springFirstSeminar.service.dto.BlogCreateRequest;
import org.sopt.springFirstSeminar.service.dto.BlogTitleUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final MemberRepository memberRepository;

    private final S3Service s3Service;
    private static final String BLOG_S3_UPLOAD_FOLER = "blog/";


    @Transactional
    public String create(Long memberId, BlogCreateRequest createRequest) {
        //member찾기
        Member member = findMemberById(memberId);
        try {
            Blog blog = blogRepository.save(Blog.create(member, createRequest.title(), createRequest.description(),
                    s3Service.uploadImage(BLOG_S3_UPLOAD_FOLER, createRequest.image())));
            return blog.getId().toString();
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional //이거를 적거나 이 메서드 아래에 blogRepository.save()를 해도 됨.
    public void updateTitle(final Long blogId, final BlogTitleUpdateRequest blogTitleUpdateRequest) {
        Blog blog = findBlogById(blogId);
        blog.updateTitle(blogTitleUpdateRequest.title());
    }

    public Blog findBlogById(final Long blogId) {
        return blogRepository.findById(blogId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.BLOG_NOT_FOUND)
        );
    }

    private Member findMemberById(final Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND)
        );
    }
}
