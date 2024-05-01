package org.sopt.springFirstSeminar.common;

import jakarta.persistence.EntityNotFoundException;
import org.sopt.springFirstSeminar.common.dto.ErrorMessage;
import org.sopt.springFirstSeminar.common.dto.ErrorResponse;
import org.sopt.springFirstSeminar.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.of(HttpStatus.BAD_REQUEST.value(), Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<BaseResponse<?>> handleNotFoundException(NotFoundException e) {
        return ApiResponseUtil.fail(e.getErrorMessage());
    }
}
