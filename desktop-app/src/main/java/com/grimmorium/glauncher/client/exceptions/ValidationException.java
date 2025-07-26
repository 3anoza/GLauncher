package com.grimmorium.glauncher.client.exceptions;

import com.grimmorium.glauncher.client.enums.ErrorCode;

public class ValidationException extends BusinessLogicException {
    public ValidationException(ErrorCode code, String message) { super(code, message); }
    public ValidationException(ErrorCode code) { super(code); }
    public ValidationException(String message) { super(message); }
    public ValidationException() { }
}
