package com.grimmorium.glauncher.core.exceptions;

import com.grimmorium.glauncher.contracts.enums.ErrorCode;

public class BusinessLogicException extends Exception {
    public final ExceptionsResult exceptionsResult;
    private static final String formatMessage = "Some error is occurred in handlers";

    public BusinessLogicException(String message) {
        super(message);
        this.exceptionsResult = new ExceptionsResult(
                ErrorCode.NoErrorCode,
                ErrorCode.NoErrorCode.value,
                message
        );
    }

    public BusinessLogicException() {
        super(formatMessage);
        this.exceptionsResult = new ExceptionsResult(
                ErrorCode.NoErrorCode,
                ErrorCode.NoErrorCode.value,
                formatMessage
        );
    }

    public BusinessLogicException(ErrorCode code) {
        super(formatMessage);
        this.exceptionsResult = new ExceptionsResult(
                code,
                code.value,
                formatMessage
        );
    }

    public BusinessLogicException(ErrorCode code, String message) {
        super(message);
        this.exceptionsResult = new ExceptionsResult(
                code,
                code.value,
                message
        );
    }
}
