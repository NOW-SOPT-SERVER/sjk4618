package org.sopt.carrotMarket.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.sopt.carrotMarket.constant.Location;
import org.sopt.carrotMarket.domain.Member;

public record RegisterItemDTO(

        @NotBlank
        String title,

        @NotNull
        int price,

        @NotNull
        boolean isReceived,

        @NotBlank
        String detailInfo,

        @NotBlank
        String hopeTradeSpot
) { }
