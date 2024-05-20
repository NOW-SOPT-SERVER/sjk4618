package org.sopt.carrotMarket.common;

import org.sopt.carrotMarket.common.dto.ErrorMessage;
import org.sopt.carrotMarket.exception.InvalidValueException;
import org.sopt.carrotMarket.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<BaseResponse<?>> handleNotFoundException(final NotFoundException e) {
        return ApiResponseUtil.failure(e.getErrorMessage());
    }

    @ExceptionHandler(InvalidValueException.class)
    protected ResponseEntity<BaseResponse<?>> handleIllegalArgumentException(final InvalidValueException e) {
        return ApiResponseUtil.failure(e.getErrorMessage());
    }
}
