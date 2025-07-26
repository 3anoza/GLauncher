package com.grimmorium.glauncher.client.controllers;

import com.grimmorium.glauncher.client.JavaBridge;
import com.grimmorium.glauncher.client.LauncherProfile;
import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class AccountController extends JavaBridge {

    public AccountController(WebEngine engine) {
        super(engine);
        Platform.runLater(() ->
                engine.executeScript(" document.getElementById('username').innerText = \"Username: "
                        + toJSString(LauncherProfile.mockUser.name) + "\";")
        );
    }

    public void openFileDialog(String nameHolder) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image file");

        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("Images (*.png)", "*.png");
        fileChooser.getExtensionFilters().setAll(pngFilter);

        File selectedFile = fileChooser.showOpenDialog(null);
        System.out.println("Holder " + nameHolder);
        if (selectedFile != null) {
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            Platform.runLater(() ->
                    engine.executeScript("window.onFileChosen(" +
                            toJSString(selectedFile.getAbsolutePath()) + ", " + toJSString(nameHolder) + ")")
            );
        }
    }

    public boolean validateFile(String filePath, int maxWidth, int maxHeight) throws IOException {
        int width = 128;
        int height = 128;
        return width <= maxWidth && height <= maxHeight;
    }

    public boolean uploadFile(String filePath) {
        return true;
    }
}
