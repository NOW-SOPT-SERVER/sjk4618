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
        String hopeTradeSpot
) {

    //이게 멤버별로 받아올 때는, 내가 좋아요 누른지가 필요하지만
    //지역별로 받아올 때는, 내가 좋아요를 누른지가 필요하지 않아서 builder를 이용했다.

    @Builder //빌더패턴
    public GetAllItemsByMemberIdResponseDTO(Long itemId, String title, int price,
                                            boolean isReceived, String detailInfo,
                                            boolean isLikedByMember, int likesCount,
                                            String hopeTradeSpot
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
                .hopeTradeSpot(String.valueOf(item.getHopeTradeSpot()))
                .build();
    }
}
