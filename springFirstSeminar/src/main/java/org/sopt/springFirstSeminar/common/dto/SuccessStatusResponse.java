package org.sopt.springFirstSeminar.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public record SuccessStatusResponse<T>(
        int status,
        String message,
        @JsonInclude(value = JsonInclude.Include.NON_NULL) //이거붙이니까 data없는 곳은 response에 안뜸(공부해야됨)!!
        T data
) {

    public static SuccessStatusResponse<?> of(SuccessMessage successMessage) {
        return builder()
                .status(successMessage.getStatus())
                .message(successMessage.getMessage())
                .build();
    }

    public static <T> SuccessStatusResponse<?> of(SuccessMessage successMessage, T data) {
        return builder()
                .status(successMessage.getStatus())
                .message(successMessage.getMessage())
                .data(data)
                .build();
    }

}
