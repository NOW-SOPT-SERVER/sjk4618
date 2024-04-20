package org.sopt.springFirstSeminar.exception;

import org.sopt.springFirstSeminar.common.dto.ErrorMessage;

public class BusinessException extends RuntimeException {

    private ErrorMessage errorMessage;

    public BusinessException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

}
