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

    //jwt
    JWT_UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), "사용자의 로그인 검증을 실패했습니다."),
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED.value(), "액세스 토큰의 형식이 올바르지 않습니다. Bearer 타입을 확인해 주세요."),
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED.value(), "액세스 토큰이 만료되었습니다. 재발급 받아주세요."),
    UNSUPPORTED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED.value(), "지원하지 않는 JWT 형식입니다."),
    EMPTY_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED.value(), "JWT Claim이 비어있습니다"),
    JWT_SIGNATURE_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), "JWT의 기존 서명과 다릅니다."),

    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED.value(), "리프레시 토큰의 형식이 올바르지 않습니다."),
    INVALID_REFRESH_TOKEN_VALUE(HttpStatus.UNAUTHORIZED.value(), "리프레시 토큰의 값이 올바르지 않습니다."),
    EXPIRED_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED.value(),"리프레시 토큰이 만료되었습니다. 다시 로그인해 주세요."),
    MISMATCH_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED.value(),  "리프레시 토큰이 일치하지 않습니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED.value(), "리프레시 토큰을 찾을 수 없습니다."),


    ;

    private final int status;
    private final String message;
}