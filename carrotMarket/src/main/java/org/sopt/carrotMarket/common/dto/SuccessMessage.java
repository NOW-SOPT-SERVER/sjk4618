package org.sopt.carrotMarket.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessMessage {
    MEMBER_REGISTER_SUCCESS(HttpStatus.CREATED.value(), "멤버 등록이 완료되었습니다."),
    ITEM_REGISTER_SUCCESS(HttpStatus.CREATED.value(), "물건 등록이 완료되었습니다."),
    GET_ITEMS_SUCCESS(HttpStatus.OK.value(), "물건들을 불러왔습니다.")
    ;
    private final int status;
    private final String message;
}
