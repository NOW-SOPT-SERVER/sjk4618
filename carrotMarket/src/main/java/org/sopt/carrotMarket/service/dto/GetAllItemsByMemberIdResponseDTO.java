package org.sopt.carrotMarket.service.dto;

import org.sopt.carrotMarket.constant.Location;
import org.sopt.carrotMarket.domain.Item;

public record GetAllItemsByMemberIdResponseDTO(
        Long itemId,
        String title,
        int price,
        boolean isReceived,
        String detailInfo,
        Location hopeTradeSpot
) {
    public static GetAllItemsByMemberIdResponseDTO of(Item item) {
        return new GetAllItemsByMemberIdResponseDTO(
                item.getId(),
                item.getTitle(),
                item.getPrice(),
                item.isReceived(),
                item.getDetailInfo(),
                item.getHopeTradeSpot()
        );
    }
}
