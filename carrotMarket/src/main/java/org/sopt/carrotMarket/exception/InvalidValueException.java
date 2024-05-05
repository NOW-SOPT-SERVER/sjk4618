package org.sopt.carrotMarket.exception;

import org.sopt.carrotMarket.common.dto.ErrorMessage;

public class InvalidValueException extends BusinessException {
    public InvalidValueException() {
        super(ErrorMessage.INVALID_INPUT);
    }

    public InvalidValueException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}