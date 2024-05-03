package org.sopt.carrotMarket.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.carrotMarket.common.ApiResponseUtil;
import org.sopt.carrotMarket.common.BaseResponse;
import org.sopt.carrotMarket.common.dto.SuccessMessage;
import org.sopt.carrotMarket.service.ItemService;
import org.sopt.carrotMarket.service.dto.RegisterItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/item")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<BaseResponse<?>> registerItem(@RequestHeader Long memberId,
                                                        @RequestBody RegisterItemDTO registerItemDTO) {
        itemService.registerItem(memberId, registerItemDTO);
        return ApiResponseUtil.success(SuccessMessage.ITEM_REGISTER_SUCCESS);
    }
}
