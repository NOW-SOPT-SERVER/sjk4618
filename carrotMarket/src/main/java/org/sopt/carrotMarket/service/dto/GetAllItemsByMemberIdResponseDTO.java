package org.sopt.carrotMarket.service.dto;

import lombok.Builder;
import org.sopt.carrotMarket.constant.Location;
import org.sopt.carrotMarket.domain.Item;
import org.sopt.carrotMarket.domain.Member;

public record GetAllItemsByMemberIdResponseDTO(
        Long itemId,
        String title,
        int price,
        boolean isReceived,
        String detailInfo,
        boolean isLikedByMember,
        int likesCount,
        Location hopeTradeSpot
) {

    @Builder //빌더패턴
    public GetAllItemsByMemberIdResponseDTO(Long itemId, String title, int price,
                                            boolean isReceived, String detailInfo,
                                            boolean isLikedByMember, int likesCount,
                                            Location hopeTradeSpot
    ) {
        this.itemId = itemId;
        this.title = title;
        this.price = price;
        this.isReceived = isReceived;
        this.detailInfo = detailInfo;
        this.isLikedByMember = isLikedByMember;
        this.likesCount = likesCount;
        this.hopeTradeSpot = hopeTradeSpot;
    }

    public static GetAllItemsByMemberIdResponseDTO of(Item item, boolean isLiked) {
        return GetAllItemsByMemberIdResponseDTO.builder()
                .itemId(item.getId())
                .title(item.getTitle())
                .price(item.getPrice())
                .isReceived(item.isReceived())
                .detailInfo(item.getDetailInfo())
                .isLikedByMember(isLiked)
                .likesCount(item.getLikesCount())
                .hopeTradeSpot(item.getHopeTradeSpot())
                .build();
    }
}
