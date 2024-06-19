package org.sopt.carrotMarket.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.carrotMarket.common.ApiResponseUtil;
import org.sopt.carrotMarket.common.BaseResponse;
import org.sopt.carrotMarket.common.dto.SuccessMessage;
import org.sopt.carrotMarket.service.ItemService;
import org.sopt.carrotMarket.service.dto.GetAllItemsInfoResponseDTO;
import org.sopt.carrotMarket.service.dto.RegisterItemDTO;
import org.sopt.carrotMarket.service.dto.RegisterItemResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.path}")
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/item")
    public ResponseEntity<BaseResponse<?>> registerItem(@RequestHeader final Long memberId,
                                                        @ModelAttribute final RegisterItemDTO registerItemDTO) {
        RegisterItemResponseDTO registerItemResponseDTO = itemService.registerItem(memberId, registerItemDTO);

        return ApiResponseUtil.success(SuccessMessage.ITEM_REGISTER_SUCCESS, registerItemResponseDTO);
    }

    //memberID에 해당되는 모든 물건 GET
    @GetMapping("/item/items")
    public ResponseEntity<BaseResponse<?>> getAllItemsByMemberId(@RequestHeader final Long memberId) {
        List<GetAllItemsInfoResponseDTO> response = itemService.getAllItemsByMemberId(memberId);
        return ApiResponseUtil.success(SuccessMessage.GET_ITEMS_SUCCESS_BY_MEMBERID, response);
    }

    //Location에 해당되는 모든 물건 GET
    @GetMapping("/item")
    public ResponseEntity<BaseResponse<?>> getAllItemsByLocation(@RequestParam(value = "location") final String location) {
        List<GetAllItemsInfoResponseDTO> response = itemService.getAllItemsByLocation(location);

        return ApiResponseUtil.success(SuccessMessage.GET_ITEMS_SUCCESS_BY_LOCATION, response);
    }

    @DeleteMapping("/item")
    public ResponseEntity<BaseResponse<?>> deleteItem(@RequestHeader final Long itemId) {
        itemService.deleteItem(itemId);
        return ApiResponseUtil.success(SuccessMessage.ITEM_DELETE_SUCCESS);
    }


}
