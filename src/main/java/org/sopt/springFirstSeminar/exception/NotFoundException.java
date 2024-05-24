package org.sopt.springFirstSeminar.exception;

import org.sopt.springFirstSeminar.common.dto.ErrorMessage;

public class NotFoundException extends BusinessException {
    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
