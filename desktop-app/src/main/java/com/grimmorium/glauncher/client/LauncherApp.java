package com.grimmorium.glauncher.client;

import com.grimmorium.glauncher.client.controllers.AccountController;
import com.grimmorium.glauncher.client.controllers.LoginController;
import com.grimmorium.glauncher.client.controllers.ServerSettingsController;
import com.grimmorium.glauncher.client.controllers.ServersController;
import javafx.application.Application;
import javafx.concurrent.Worker.State;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class LauncherApp extends Application {
    private WebEngine engine;

    @Override
    public void start(Stage stage) throws Exception {
        WebView view = new WebView();
        engine = view.getEngine();

        engine.getLoadWorker().stateProperty().addListener((obs, old, state) -> {
            if (state == State.SUCCEEDED) {
                String location = engine.getLocation();
                int localPathIndex = location.lastIndexOf("/ui/");
                location = location.substring(localPathIndex + 4);
                JSObject window = (JSObject)engine.executeScript("window");

                switch(location) {
                    case "login.html":
                        window.setMember("pageController", new LoginController(engine));
                        break;
                    case "servers.html":
                        window.setMember("pageController", new ServersController(engine));
                        break;
                    case "account.html":
                        window.setMember("pageController", new AccountController(engine));
                        break;
                    case "settings.html":
                        window.setMember("pageController", new ServerSettingsController(engine));
                        break;
                    default:
                        window.setMember("launcher", new HtmlController(engine));
                }
            }

        });
        engine.load(this.getClass().getResource("/ui/login.html").toExternalForm());
        stage.setScene(new Scene(view, 800.0D, 600.0D));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
