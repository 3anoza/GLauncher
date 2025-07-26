package com.grimmorium.glauncher.client.controllers;

import com.grimmorium.glauncher.client.JavaBridge;
import com.grimmorium.glauncher.client.LauncherProfile;
import com.grimmorium.glauncher.client.enums.HttpMethod;
import com.grimmorium.glauncher.client.events.EventSystem;
import com.grimmorium.glauncher.client.net.commands.authorize.LoginRequest;
import com.grimmorium.glauncher.client.net.commands.authorize.LoginResponse;
import com.grimmorium.glauncher.client.net.commands.users.UserResponse;
import com.grimmorium.glauncher.client.net.services.HttpLauncherClient;
import javafx.application.Platform;
import javafx.scene.web.WebEngine;

import java.util.concurrent.CompletableFuture;

public class LoginController extends JavaBridge {
    private final int maxPasswordLength = 255;
    private final int maxUsernameLength = 20;
    private final int minUsernameLength = 3;
    private final int minPasswordLength = 10;

    public LoginController(WebEngine engine) {
        super(engine);
        endpointAddress = "";
    }

    public boolean login(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            showErrorMsg("Username or password is empty");
            return false;
        }

        if (username.length() > maxUsernameLength || username.length() < minUsernameLength) {
            showErrorMsg("Username should be between " + minUsernameLength + " and "
                    + maxUsernameLength + " characters");
            return false;
        }

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
        }

        System.out.println("Login successful");
        UserResponse user = new UserResponse();
        user.name = username;
        user.id = "0";
        LauncherProfile.mockUser = user;
        return true;
    }

    public void showErrorMsg(String msg) {
        Platform.runLater(() ->
                engine.executeScript("document.getElementById('error').innerText = " + toJSString(msg) + ";")
        );
    }
}
