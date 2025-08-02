package com.grimmorium.glauncher.client;

import javafx.scene.web.WebEngine;

public class HtmlController {
    protected final WebEngine engine;
    protected String endpointAddress = "";

    public HtmlController(WebEngine engine) {
        this.engine = engine;
    }

    public void navigateTo(String url) {
        try {
            engine.load(this.getClass().getResource(url).toExternalForm());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeTheme(String theme) {}
    public void changeLanguage(String language){}
    public void printJ(String text){
        System.out.println(text);
    }

    protected String toJSString(String s) {
        // Escape for JS string literal
        return "'" + s.replace("'", "\\'")
                .replace("\"", "\\\"")
                .replace("\\", "/") + "'";
    }

}
