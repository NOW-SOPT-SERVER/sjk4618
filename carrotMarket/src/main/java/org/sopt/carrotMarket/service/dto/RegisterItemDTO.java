package org.sopt.carrotMarket.service.dto;

import org.sopt.carrotMarket.constant.Location;
import org.sopt.carrotMarket.domain.Member;

public record RegisterItemDTO(
        String title,
        int price,
        boolean isReceived,
        String detailInfo,
        String hopeTradeSpot
) { }
