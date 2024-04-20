package org.sopt.springFirstSeminar.common.dto;

public record SuccesttStatusResponse(
        int status,
        String message
) {
    public static SuccesttStatusResponse of(SuccessMessage successMessage) {

        return new SuccesttStatusResponse(successMessage.getStatus(), successMessage.getMessage());

    }
}
