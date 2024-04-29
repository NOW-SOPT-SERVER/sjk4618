package org.sopt.carrotMarket.service.dto;

public record RegisterItemDTO(
        String title,
        int price,
        boolean isReceived,
        String detailInfo,
        String hopeTradeSpot
) { }
