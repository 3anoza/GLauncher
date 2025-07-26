package com.grimmorium.glauncher.client.enums;

public enum ErrorCode {
    NoErrorCode(0x00),
    ValidationException(0x01),
    Conflict(0x02),
    UserNotFound(0x03);

    public final int value;
    ErrorCode(int value){
        this.value = value;
    }
}
