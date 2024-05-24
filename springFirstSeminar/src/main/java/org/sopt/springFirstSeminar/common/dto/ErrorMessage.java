package org.sopt.springFirstSeminar.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 사용자가 존재하지 않습니다."),
    BLOG_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "BLOG ID에 해당하는 블로그가 존재하지 않습니다."),
    BLOG_NOT_MATCH_MEMBER(HttpStatus.NOT_FOUND.value(), "해당 멤버 ID에 해당하는 블로그ID가 아닙니다."),
    CONTENT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "POST ID에 해당하는 글이 존재하지 않습니다"),
    MAX_BLOG_CONTENT(HttpStatus.BAD_REQUEST.value(), "블로그 글이 최대 글자 수(20)를 초과했습니다"),
    JWT_UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), "사용자의 로그인 검증을 실패했습니다.")
    ;

    private final int status;
    private final String message;
}