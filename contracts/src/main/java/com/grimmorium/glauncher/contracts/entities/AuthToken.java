package com.grimmorium.glauncher.contracts.entities;

import java.time.LocalDateTime;

public class AuthToken {
    public final String accessToken;
    public final String refreshToken;
    public final LocalDateTime issuedAt;
    public final LocalDateTime expiresAt;

    public AuthToken(String accessToken, String refreshToken, LocalDateTime issuedAt, LocalDateTime expiresAt) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
    }
}
