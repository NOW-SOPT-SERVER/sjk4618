package org.sopt.carrotMarket.exception;

import org.sopt.carrotMarket.common.dto.ErrorMessage;

public class NotFoundException extends BusinessException {
    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}