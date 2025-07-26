package com.grimmorium.glauncher.client.controllers;

import com.grimmorium.glauncher.client.JavaBridge;
import com.grimmorium.glauncher.client.events.EventSystem;
import com.grimmorium.glauncher.client.net.commands.servers.ServerResponse;
import com.grimmorium.glauncher.client.utils.Serializer;
import javafx.application.Platform;
import javafx.scene.web.WebEngine;

import java.util.ArrayList;
import java.util.List;

public class ServersController extends JavaBridge {
    private ServerResponse mockedServer;

    public ServersController(WebEngine engine) {
        super(engine);

        System.out.println("ServersController constructor");

        endpointAddress = "";
        mockedServer = new ServerResponse();
        mockedServer.online = 0;
        mockedServer.version = "1.7.10";
        mockedServer.maxOnline = 20;
        mockedServer.description = "Server #1 for Minecraft";

        loadServers();
    }

    public void loadServers() {
        System.out.println("Loading Servers");
        List<ServerResponse> servers = fetchServers();
        String json = Serializer.getSerializer().Serialize(servers);
        Platform.runLater(() -> {
            engine.executeScript("window.renderServers("+json+");");
        });
    }

    public void onPlay(int server) {
        System.out.println("Server chosen: "+server);
    }

    public void onEditSettings(int server) {
        System.out.println("Settings edits for server: "+server);
        System.out.println("Loading profile...");
    }

    public void printJ(String text){
        System.out.println(text);
    }

    private List<ServerResponse> fetchServers() {
        List<ServerResponse> servers = new ArrayList<>();
        servers.add(mockedServer);
        servers.add(cloneServer(mockedServer, "Server #2 for Minecraft"));
        servers.add(cloneServer(mockedServer,  "Server #3 for Minecraft"));
        servers.add(cloneServer(mockedServer, "Server #4 for Minecraft"));
        return servers;
    }

    private ServerResponse cloneServer(ServerResponse server, String description) {
        ServerResponse clone = new ServerResponse();
        clone.description = description;
        clone.version = server.version;
        clone.maxOnline = server.maxOnline;
        clone.online = server.online;
        return clone;
    }
}
