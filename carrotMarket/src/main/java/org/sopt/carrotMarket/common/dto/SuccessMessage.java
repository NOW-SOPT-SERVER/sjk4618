package org.sopt.carrotMarket.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SuccessMessage {
    ;
    private final int status;
    private final String message;
}
