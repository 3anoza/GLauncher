package com.grimmorium.glauncher.core.exceptions;

import com.grimmorium.glauncher.contracts.enums.ErrorCode;

public class ValidationException extends BusinessLogicException {
    public ValidationException(ErrorCode code, String message) { super(code, message); }
    public ValidationException(ErrorCode code) { super(code); }
    public ValidationException(String message) { super(message); }
    public ValidationException() { }
}
