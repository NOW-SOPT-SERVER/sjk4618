package org.sopt.springFirstSeminar.exception;

import org.sopt.springFirstSeminar.common.dto.ErrorMessage;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
