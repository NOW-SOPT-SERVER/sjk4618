package org.sopt.springFirstSeminar.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.common.dto.SuccessMessage;
import org.sopt.springFirstSeminar.common.dto.SuccessStatusResponse;
import org.sopt.springFirstSeminar.service.BlogService;
import org.sopt.springFirstSeminar.service.dto.BlogCreateRequest;
import org.sopt.springFirstSeminar.service.dto.BlogTitleUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/blog")
    public ResponseEntity<SuccessStatusResponse<?>> createBlog(
            @RequestHeader(name = "memberId") Long memberId,
            @RequestBody BlogCreateRequest blogCreateRequest
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", blogService.create(memberId, blogCreateRequest))
                .body(SuccessStatusResponse.of(SuccessMessage.BLOG_CREATE_SUCCESS));
    }

    @PatchMapping("/blog/{blogId}/title")
    public ResponseEntity updateBlogTitle(
            @PathVariable Long blogId,
            @Valid @RequestBody BlogTitleUpdateRequest blogTitleUpdateRequest) {
        blogService.updateTitle(blogId, blogTitleUpdateRequest);
        return ResponseEntity.noContent().build();
    }

}
