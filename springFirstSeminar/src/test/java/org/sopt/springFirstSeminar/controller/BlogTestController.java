package org.sopt.springFirstSeminar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sopt.springFirstSeminar.repository.BlogRepository;
import org.sopt.springFirstSeminar.repository.MemberRepository;
import org.sopt.springFirstSeminar.service.BlogService;
import org.sopt.springFirstSeminar.service.MemberService;
import org.sopt.springFirstSeminar.service.dto.BlogCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BlogController.class)
@AutoConfigureMockMvc
public class BlogTestController {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private BlogService blogService;

    @SpyBean
    private MemberService memberService;

    @MockBean
    private BlogRepository blogRepository;

    @MockBean
    private MemberRepository memberRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    class createBlog {

        @Test
        @DisplayName("Blog 생성 실패 테스트")
        public void createBlogFail() throws Exception {

            //given
            String request = objectMapper.writeValueAsString(new BlogCreateRequest("성준블로그", "블로그설명"));

            mockMvc.perform(
                    post("/api/v1/blog")
                            .content(request).header("memberId", 10).contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isNotFound())
                    .andDo(print());

        }
    }



}
