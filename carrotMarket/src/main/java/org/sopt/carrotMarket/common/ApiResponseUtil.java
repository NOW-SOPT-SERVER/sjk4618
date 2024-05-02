package org.sopt.carrotMarket.common;

import org.sopt.carrotMarket.common.dto.ErrorMessage;
import org.sopt.carrotMarket.common.dto.SuccessMessage;
import org.springframework.http.ResponseEntity;

public interface ApiResponseUtil {
    static ResponseEntity<BaseResponse<?>> success(SuccessMessage successMessage) {
        return ResponseEntity
                .status(successMessage.getStatus())
                .body(BaseResponse.of(successMessage));
    }

    static <T> ResponseEntity<BaseResponse<?>> success(SuccessMessage successMessage, T data) {
        return ResponseEntity
                .status(successMessage.getStatus())
                .body(BaseResponse.of(successMessage, data));
    }

    static ResponseEntity<BaseResponse<?>> failure(ErrorMessage errorMessage) {
        return ResponseEntity
                .status(errorMessage.getStatus())
                .body(BaseResponse.of(errorMessage));
    }
}
