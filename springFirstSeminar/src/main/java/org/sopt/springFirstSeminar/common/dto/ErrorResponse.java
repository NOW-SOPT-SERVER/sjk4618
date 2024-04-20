package org.sopt.springFirstSeminar.common.dto;

public record ErrorResponse(
        int status,
        String message //에러 발생 이유
) {
    public static ErrorResponse of(int status, String message) {
        return new ErrorResponse(status, message);
    }

    public static ErrorResponse of(ErrorMessage errorMessage) {
        return new ErrorResponse(errorMessage.getStatus(), errorMessage.getMessage());
    }
}
