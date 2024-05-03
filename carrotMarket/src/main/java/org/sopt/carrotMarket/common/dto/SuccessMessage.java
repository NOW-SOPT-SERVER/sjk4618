package org.sopt.carrotMarket.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessMessage {
    MEMBER_REGISTER_SUCCESS(HttpStatus.CREATED.value(), "멤버 등록이 완료되었습니다."),
    ITEM_REGISTER_SUCCESS(HttpStatus.CREATED.value(), "물건 등록이 완료되었습니다."),
    GET_ITEMS_SUCCESS_BY_MEMBERID(HttpStatus.OK.value(), "해당 MemberId에 해당하는 물건들을 불러왔습니다."),
    GET_ITEMS_SUCCESS_BY_LOCATION(HttpStatus.OK.value(), "해당 지역 물건들을 불러왔습니다."),
    ITEM_LIKES_BY_MEMBERID_SUCCESS(HttpStatus.OK.value(), "해당 물품에 좋아요를 눌렀습니다.")
    ;
    private final int status;
    private final String message;
}
