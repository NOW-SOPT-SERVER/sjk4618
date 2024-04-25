package org.sopt.springFirstSeminar.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 사용자가 존재하지 않습니다."),
    BLOG_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "BLOG ID에 해당하는 블로그가 존재하지 않습니다."),
    BLOG_NOT_MATCH_MEMBER(HttpStatus.NOT_FOUND.value(), "해당 멤버 ID에 해당하는 블로그가 존재하지 않습니다."),
    ;

    private final int status;
    private final String message;
}