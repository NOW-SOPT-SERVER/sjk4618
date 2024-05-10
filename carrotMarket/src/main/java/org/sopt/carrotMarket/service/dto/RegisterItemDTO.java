package org.sopt.carrotMarket.service.dto;

import jakarta.validation.constraints.NotBlank;
import org.sopt.carrotMarket.constant.Location;
import org.sopt.carrotMarket.domain.Member;

public record RegisterItemDTO(

        @NotBlank
        String title,

        @NotBlank
        int price,

        @NotBlank
        boolean isReceived,

        @NotBlank
        String detailInfo,

        @NotBlank
        String hopeTradeSpot
) { }
