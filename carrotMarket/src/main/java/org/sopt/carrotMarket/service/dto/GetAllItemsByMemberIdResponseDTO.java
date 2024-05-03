package org.sopt.carrotMarket.service.dto;

import org.sopt.carrotMarket.constant.Location;
import org.sopt.carrotMarket.domain.Item;

public record GetAllItemsInfoResponseDTO(
        Long itemId,
        String title,
        int price,
        boolean isReceived,
        String detailInfo,
        boolean isLikedByMember,
        int likesCount,
        Location hopeTradeSpot
) {
    public static GetAllItemsInfoResponseDTO of(Item item, boolean isLiked) { //itemService에서 그 멤버가
        return new GetAllItemsInfoResponseDTO(
                item.getId(),
                item.getTitle(),
                item.getPrice(),
                item.isReceived(),
                item.getDetailInfo(),
                isLiked,
                item.getLikesCount(),
                item.getHopeTradeSpot()
        );
    }
}
