package org.sopt.carrotMarket.service.dto;

import org.sopt.carrotMarket.constant.Location;
import org.sopt.carrotMarket.domain.Item;

public record GetAllItemsByLocationResponseDTO(
        Long itemId,
        String title,
        int price,
        boolean isReceived,
        String detailInfo,
        int likesCount,
        Location hopeTradeSpot
) {
    public static GetAllItemsByLocationResponseDTO of(Item item) {
        return new GetAllItemsByLocationResponseDTO(
                item.getId(),
                item.getTitle(),
                item.getPrice(),
                item.isReceived(),
                item.getDetailInfo(),
                item.getLikesCount(),
                item.getHopeTradeSpot()
        );
    }
}
