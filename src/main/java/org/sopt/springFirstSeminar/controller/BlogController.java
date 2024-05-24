package org.sopt.springFirstSeminar.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.springFirstSeminar.common.jwt.auth.filter.PrincipalHandler;
import org.sopt.springFirstSeminar.service.BlogService;
import org.sopt.springFirstSeminar.service.dto.BlogCreateRequest;
import org.sopt.springFirstSeminar.service.dto.BlogTitleUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final PrincipalHandler principalHandler;

    @PostMapping("/blog")
    public ResponseEntity createBlog(
            @ModelAttribute BlogCreateRequest blogCreateRequest
    ) {
        return ResponseEntity.created(URI.create(blogService.create(
                principalHandler.getUserIdFromPrincipal(), blogCreateRequest))).build();
    }

    @PatchMapping("/blog/{blogId}/title")
    public ResponseEntity updateBlogTitle(
            @PathVariable final Long blogId,
            @Valid @RequestBody final BlogTitleUpdateRequest blogTitleUpdateRequest) {
        blogService.updateTitle(blogId, blogTitleUpdateRequest);
        return ResponseEntity.noContent().build();
    }
}
