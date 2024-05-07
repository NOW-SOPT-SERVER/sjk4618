package org.sopt.springFirstSeminar.common;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.sopt.springFirstSeminar.common.dto.ErrorMessage;
import org.sopt.springFirstSeminar.common.dto.SuccessMessage;
import org.sopt.springFirstSeminar.common.dto.SuccessStatusResponse;

@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class BaseResponse<T> {
    private final int status;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    public static BaseResponse<?> of(SuccessMessage successMessage) {
        return builder()
                .status(successMessage.getStatus())
                .message(successMessage.getMessage())
                .build();
    }

    public static <T> BaseResponse<?> of(SuccessMessage successMessage, T data) {
        return builder()
                .status(successMessage.getStatus())
                .message(successMessage.getMessage())
                .data(data)
                .build();
    }

    public static BaseResponse<?> of(ErrorMessage errorMessage) {
        return builder()
                .status(errorMessage.getStatus())
                .message(errorMessage.getMessage())
                .build();
    }

}
