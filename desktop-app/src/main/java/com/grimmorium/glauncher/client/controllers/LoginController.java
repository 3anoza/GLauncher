package com.grimmorium.glauncher.client.controllers;

import com.grimmorium.glauncher.client.HtmlController;
import com.grimmorium.glauncher.client.LauncherProfile;
import com.grimmorium.glauncher.contracts.entities.AuthToken;
import com.grimmorium.glauncher.contracts.enums.HttpMethod;
import com.grimmorium.glauncher.client.net.commands.authorize.LoginRequest;
import com.grimmorium.glauncher.client.net.commands.authorize.LoginResponse;
import com.grimmorium.glauncher.core.net.services.HttpLauncherClient;
import javafx.application.Platform;
import javafx.scene.web.WebEngine;

import java.util.concurrent.CompletableFuture;

public class LoginController extends HtmlController {

    public LoginController(WebEngine engine) {
        super(engine);
        endpointAddress = "";
    }

    public boolean login(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            showErrorMsg("Username or password is empty");
            return false;
        }

        int maxUsernameLength = 20;
        int minUsernameLength = 3;
        if (username.length() > maxUsernameLength || username.length() < minUsernameLength) {
            showErrorMsg("Username should be between " + minUsernameLength + " and "
                    + maxUsernameLength + " characters");
            return false;
        }

        int maxPasswordLength = 255;
        int minPasswordLength = 10;
        if (password.length() > maxPasswordLength || password.length() < minPasswordLength) {
            showErrorMsg("Password should be between " + minPasswordLength + " and "
                    + maxPasswordLength + " characters");
            return false;
        }

        if (!endpointAddress.isEmpty() || !endpointAddress.equals("")) {
            CompletableFuture<LoginResponse> future = HttpLauncherClient.getInstance()
                    .sendRequestAsync(endpointAddress, HttpMethod.POST, new LoginRequest(username, password));

            LoginResponse response = future.join();

            if (response == null) {
                showErrorMsg("Login failed. Try again later.");
                return false;
            }

            LauncherProfile.authToken = new AuthToken(
                    response.accessToken,
                    response.refreshToken,
                    response.issuedAt,
                    response.expiresAt
            );
        }

        System.out.println("Login successful");
        return true;
    }

    public void showErrorMsg(String msg) {
        Platform.runLater(() ->
                engine.executeScript("document.getElementById('error').innerText = " + toJSString(msg) + ";")
        );
    }
}
