package com.grimmorium.glauncher.core.exceptions;

import com.grimmorium.glauncher.contracts.enums.ErrorCode;

import java.util.Dictionary;
import java.util.List;

public class ExceptionsResult {
    public ErrorCode errorCodeName = ErrorCode.NoErrorCode;
    public int errorCode = ErrorCode.NoErrorCode.value;
    public String message;
    public Dictionary<String, List<String>> errorDetails;

    public ExceptionsResult(ErrorCode errorCodeName, int errorCode, String message) {
        this.errorCodeName = errorCodeName;
        this.errorCode = errorCode;
        this.message = message;
    }
}
