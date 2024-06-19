package org.sopt.carrotMarket.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "MemberID에 해당하는 멤버가 없습니다."),
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당하는 아이템이 없습니다"),
    LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당하는 지역 이름이 없습니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST.value(), "잘못된 파라미터 입력이 있습니다."),
    ALREADY_ITEM_LIKED(HttpStatus.ALREADY_REPORTED.value(), "이미 좋아요가 눌려있습니다.")
    ;
    private final int status;
    private final String message;
}
