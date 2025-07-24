package com.grimmorium.glauncher.client;

import javafx.application.Application;
import javafx.concurrent.Worker.State;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class LauncherApp extends Application {
    private JavaBridge bridge;

    @Override
    public void start(Stage stage) throws Exception {
        WebView view = new WebView();
        WebEngine engine = view.getEngine();
        this.bridge = new JavaBridge(engine);
        engine.getLoadWorker().stateProperty().addListener((obs, old, state) -> {
            if (state == State.SUCCEEDED) {
                JSObject window = (JSObject)engine.executeScript("window");
                window.setMember("hostApp", this.bridge);
            }

        });
        engine.load(this.getClass().getResource("/ui/index.html").toExternalForm());
        stage.setScene(new Scene(view, 800.0D, 600.0D));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
