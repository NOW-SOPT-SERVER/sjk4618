package org.sopt.carrotMarket.common;

import org.sopt.carrotMarket.common.dto.ErrorMessage;
import org.sopt.carrotMarket.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<BaseResponse<?>> handleNotFoundException(NotFoundException e) {
        return ApiResponseUtil.failure(e.getErrorMessage());
    }
}
