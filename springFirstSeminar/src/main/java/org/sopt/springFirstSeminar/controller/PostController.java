package org.sopt.springFirstSeminar.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.common.dto.SuccessMessage;
import org.sopt.springFirstSeminar.common.dto.SuccessStatusResponse;
import org.sopt.springFirstSeminar.service.PostService;
import org.sopt.springFirstSeminar.service.dto.BlogContentRequestDTO;
import org.sopt.springFirstSeminar.service.dto.BlogContentResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post/{blogId}")
    public ResponseEntity<SuccessStatusResponse<?>> postBlogContent(
            @RequestHeader(name = "memberId") Long memberId,
            @PathVariable Long blogId,
            @Valid @RequestBody BlogContentRequestDTO blogContentRequestDTO
            ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", postService.postContent(memberId, blogId, blogContentRequestDTO))
                .body(SuccessStatusResponse.of(SuccessMessage.BLOG_CONTENT_CREATE_SUCCESS));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<SuccessStatusResponse<?>> getPostContent(
            @PathVariable Long postId
    ) {
        final BlogContentResponseDTO response = postService.getBlogContent(postId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessStatusResponse.of(SuccessMessage.GET_BLOG_CONTENT_SUCCESS, response));
    }


}
