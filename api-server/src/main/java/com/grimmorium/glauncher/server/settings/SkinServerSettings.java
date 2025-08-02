package com.grimmorium.glauncher.server.settings;

import java.util.Map;

public class SkinServerSettings {
    private String serverUrl;
    // <HttpMethod, Route>, for example: "endpoints": {"GET":"/skin", "POST":"/skin"}
    private Map<String,String> endpoints;
}
