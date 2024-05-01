package org.sopt.springFirstSeminar.common;


import com.fasterxml.jackson.databind.ser.Serializers;
import org.sopt.springFirstSeminar.common.dto.ErrorMessage;
import org.sopt.springFirstSeminar.common.dto.SuccessMessage;
import org.springframework.http.ResponseEntity;

public interface ApiResponseUtil {

    static ResponseEntity<BaseResponse<?>> success(SuccessMessage successMessage) {
        return ResponseEntity
                .status(successMessage.getStatus())
                .body(BaseResponse.of(successMessage));
    }

    static <T> ResponseEntity<BaseResponse<?>> success(SuccessMessage successMessage, T data) {
        return ResponseEntity
                .status((successMessage.getStatus()))
                .body(BaseResponse.of(successMessage, data));
    }

    static ResponseEntity<BaseResponse<?>> fail(ErrorMessage errorMessage) {
        return ResponseEntity
                .status(errorMessage.getStatus())
                .body(BaseResponse.of(errorMessage));
    }

}
