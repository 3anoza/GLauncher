package com.grimmorium.glauncher.client.controllers;

import com.grimmorium.glauncher.client.HtmlController;
import com.grimmorium.glauncher.client.LauncherProfile;
import com.grimmorium.glauncher.client.net.commands.users.UpdateUserRequest;
import com.grimmorium.glauncher.client.net.commands.users.UserResponse;
import com.grimmorium.glauncher.contracts.enums.HttpMethod;
import com.grimmorium.glauncher.core.net.services.HttpLauncherClient;
import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.CompletableFuture;

public class AccountController extends HtmlController {

    public AccountController(WebEngine engine) {
        super(engine);
        Platform.runLater(() ->
                engine.executeScript(" document.getElementById('username').innerText = \"Username: "
                        + toJSString("NickName_NickName") + "\";")
        );
    }

    public void openFileDialog(String holderName) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image file");

        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("Images (*.png)", "*.png");
        fileChooser.getExtensionFilters().setAll(pngFilter);

        File selectedFile = fileChooser.showOpenDialog(null);
        System.out.println("Holder " + holderName);
        if (selectedFile != null) {
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            Platform.runLater(() ->
                    engine.executeScript("window.onFileChosen(" +
                            toJSString(selectedFile.getAbsolutePath()) + ", " + toJSString(holderName) + ")")
            );
        }
    }

    public void loadImage(String filePath, String holderName) {
        File file = new File(filePath);
        if (!file.exists()) return;
        String absolutePath = file.getAbsolutePath();
        Platform.runLater(() -> {
            engine.executeScript("document.getElementById("+ toJSString(holderName)
                    +").setAttribute(\"src\", "+ toJSString(absolutePath) + ");");
        });
    }

    public boolean uploadFile(String filePath, String holderName) {
        File file = new File(filePath);
        if (!file.exists()) return false;
        UpdateUserRequest request = new UpdateUserRequest();
        request.authToken = LauncherProfile.authToken;

        try{
            switch (holderName) {
                case "avatar":
                    request.avatar = Files.readAllBytes(file.toPath());
                    break;
                case "skin":
                    request.skin = Files.readAllBytes(file.toPath());
                    break;
                case "cape":
                    request.cape = Files.readAllBytes(file.toPath());
                    break;
                default: return false;
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        CompletableFuture<UserResponse> future = HttpLauncherClient.getInstance()
                .sendRequestAsync(endpointAddress, HttpMethod.POST, request);

        UserResponse response = future.join();

        if (response == null) return false;


        return true;
    }

    private void loadUserInfo(UserResponse userInfo) {
    }
}
