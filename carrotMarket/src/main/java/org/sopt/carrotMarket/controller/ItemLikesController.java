package org.sopt.carrotMarket.controller;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.sopt.carrotMarket.common.ApiResponseUtil;
import org.sopt.carrotMarket.common.BaseResponse;
import org.sopt.carrotMarket.common.dto.SuccessMessage;
import org.sopt.carrotMarket.domain.ItemLikes;
import org.sopt.carrotMarket.service.ItemLikesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.path}")
public class ItemLikesController {

    private final ItemLikesService itemLikesService;

    @PostMapping("item/{itemId}")
    public ResponseEntity<BaseResponse<?>> addItemLike(@RequestHeader Long memberId,
                                                       @PathVariable Long itemId) {
        itemLikesService.addLikesByMemberId(memberId, itemId);

        return ApiResponseUtil.success(SuccessMessage.ITEM_LIKES_BY_MEMBERID_SUCCESS);
    }

}
