package org.sopt.springFirstSeminar.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.common.ApiResponseUtil;
import org.sopt.springFirstSeminar.common.BaseResponse;
import org.sopt.springFirstSeminar.common.dto.SuccessMessage;
import org.sopt.springFirstSeminar.common.dto.SuccessStatusResponse;
import org.sopt.springFirstSeminar.service.PostService;
import org.sopt.springFirstSeminar.service.dto.BlogAllContentResponseDTO;
import org.sopt.springFirstSeminar.service.dto.BlogContentRequestDTO;
import org.sopt.springFirstSeminar.service.dto.BlogContentResponseDTO;
import org.springframework.http.HttpStatus;
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
            @RequestHeader(name = "memberId") Long memberId,
            @PathVariable Long blogId,
            @Valid @RequestBody BlogContentRequestDTO blogContentRequestDTO
            ) {

        postService.postContent(memberId, blogId, blogContentRequestDTO);
        return ApiResponseUtil.success(SuccessMessage.BLOG_CONTENT_CREATE_SUCCESS);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<BaseResponse<?>> getPostContent(
            @PathVariable Long postId
    ) {
        final BlogContentResponseDTO response = postService.getBlogContent(postId);
        return ApiResponseUtil.success(SuccessMessage.GET_BLOG_CONTENT_SUCCESS, response);
    }

    @GetMapping("/post/{blogId}")
    public ResponseEntity<BaseResponse<?>> getAllPostContent(
            @PathVariable Long blogId
    ) {
        final List<BlogAllContentResponseDTO> response = postService.getBlogAllContent(blogId);
        return ApiResponseUtil.success(SuccessMessage.GET_BLOG_CONTENT_SUCCESS, response);
    }
}
