package org.sopt.carrotMarket.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessMessage {
    MEMBER_CREATE_SUCCESS(HttpStatus.CREATED.value(), "멤버 등록이 완료되었습니다.")
    ;
    private final int status;
    private final String message;
}
