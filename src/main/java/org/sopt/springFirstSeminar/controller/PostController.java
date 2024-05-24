package org.sopt.springFirstSeminar.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.common.ApiResponseUtil;
import org.sopt.springFirstSeminar.common.BaseResponse;
import org.sopt.springFirstSeminar.common.dto.SuccessMessage;
import org.sopt.springFirstSeminar.service.PostService;
import org.sopt.springFirstSeminar.service.dto.BlogAllContentResponseDTO;
import org.sopt.springFirstSeminar.service.dto.BlogContentRequestDTO;
import org.sopt.springFirstSeminar.service.dto.BlogContentResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post/{blogId}")
    public ResponseEntity<BaseResponse<?>> postBlogContent(
            @RequestHeader(name = "memberId") final Long memberId,
            @PathVariable final Long blogId,
            @Valid @RequestBody final BlogContentRequestDTO blogContentRequestDTO
            ) {
        postService.postContent(memberId, blogId, blogContentRequestDTO);
        return ApiResponseUtil.success(SuccessMessage.BLOG_CONTENT_CREATE_SUCCESS);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<BaseResponse<?>> getPostContent(
            @PathVariable final Long postId
    ) {
        final BlogContentResponseDTO response = postService.getBlogContent(postId);
        return ApiResponseUtil.success(SuccessMessage.GET_BLOG_CONTENT_SUCCESS, response);
    }

    @GetMapping("/post/blog/{blogId}")
    public ResponseEntity<BaseResponse<?>> getAllPostContent(
            @PathVariable final Long blogId
    ) {
        final List<BlogAllContentResponseDTO> response = postService.getBlogAllContent(blogId);
        return ApiResponseUtil.success(SuccessMessage.GET_BLOG_CONTENT_SUCCESS, response);
    }
}
