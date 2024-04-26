package org.sopt.springFirstSeminar.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.common.dto.SuccessMessage;
import org.sopt.springFirstSeminar.common.dto.SuccesttStatusResponse;
import org.sopt.springFirstSeminar.service.PostService;
import org.sopt.springFirstSeminar.service.dto.BlogContentRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post/{blogId}")
    public ResponseEntity<SuccesttStatusResponse> postBlogContent(
            @RequestHeader(name = "memberId") Long memberId,
            @PathVariable Long blogId,
            @Valid @RequestBody BlogContentRequestDTO blogContentRequestDTO
            ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", postService.postContent(memberId, blogId, blogContentRequestDTO))
                .body(SuccesttStatusResponse.of(SuccessMessage.BLOG_CONTENT_CREATE_SUCCESS));
    }


}
