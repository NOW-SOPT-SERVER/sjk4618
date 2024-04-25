package org.sopt.springFirstSeminar.service;

import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.common.dto.ErrorMessage;
import org.sopt.springFirstSeminar.domain.Blog;
import org.sopt.springFirstSeminar.domain.Post;
import org.sopt.springFirstSeminar.exception.NotFoundException;
import org.sopt.springFirstSeminar.repository.BlogRepository;
import org.sopt.springFirstSeminar.repository.PostRepository;
import org.sopt.springFirstSeminar.service.dto.BlogContentRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BlogService blogService;

    @Transactional
    public String postContent(Long memberId, Long blogId, BlogContentRequestDTO blogContentRequestDTO) {
        Blog blog = blogService.findBlogById(blogId); //이 메서드때문에 위에서 블로그 서비스를 가져오는지 좋은지, 아니면 그냥 이 파일에서 새로 메서드를 짜는게 좋을지?
        if(blog.getMember().getId().equals(memberId)) {
            Post post = postRepository.save(Post.create(blog, blogContentRequestDTO));
            return post.getId().toString();
        } else {
            throw new NotFoundException(ErrorMessage.BLOG_NOT_MATCH_MEMBER);
        }
    }




}
