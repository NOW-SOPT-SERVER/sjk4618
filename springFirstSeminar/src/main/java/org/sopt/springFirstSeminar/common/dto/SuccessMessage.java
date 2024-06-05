package org.sopt.springFirstSeminar.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aspectj.bridge.IMessage;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessMessage {

    MEMBER_CREATE_SUCCESS(HttpStatus.CREATED.value(), "유저 생성에 성공했습니다."),
    MEMBER_FIND_SUCCESS(HttpStatus.OK.value(), "유저 검색에 성공했습니다."),


    BLOG_CREATE_SUCCESS(HttpStatus.CREATED.value(), "블로그 생성이 완료되었습니다."),
    BLOG_CONTENT_CREATE_SUCCESS(HttpStatus.CREATED.value(), "블로그에 글 작성이 완료되었습니다."),
    GET_BLOG_CONTENT_SUCCESS(HttpStatus.OK.value(), "블로그 글 가져오기가 완료되었습니다."),

    TOKEN_REISSUE_SUCCESS(HttpStatus.OK.value(), "토큰 재발급에 성공했습니다"),
    ;
    private final int status;
    private final String message;
}
