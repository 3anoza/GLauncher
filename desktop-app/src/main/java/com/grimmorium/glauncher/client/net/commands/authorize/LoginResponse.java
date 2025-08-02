package com.grimmorium.glauncher.client.net.commands.authorize;


import java.time.LocalDateTime;

public class LoginResponse {
    public String accessToken;
    public String refreshToken;
    public LocalDateTime expiresAt;
    public LocalDateTime issuedAt;
}
