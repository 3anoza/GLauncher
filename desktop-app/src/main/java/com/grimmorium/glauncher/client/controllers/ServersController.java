package com.grimmorium.glauncher.client.controllers;

import com.grimmorium.glauncher.client.HtmlController;
import com.grimmorium.glauncher.client.net.commands.servers.QueryServersRequest;
import com.grimmorium.glauncher.client.net.commands.servers.ServerResponse;
import com.grimmorium.glauncher.contracts.entities.DownloaderInfo;
import com.grimmorium.glauncher.contracts.interfaces.IPaginatedList;
import com.grimmorium.glauncher.core.net.services.HttpLauncherClient;
import com.grimmorium.glauncher.core.services.FilesDownloader;
import com.grimmorium.glauncher.core.utils.Serializer;
import javafx.application.Platform;
import javafx.scene.web.WebEngine;

import java.util.ArrayList;
import java.util.List;

public class ServersController extends HtmlController {
    private final ServerResponse mockedServer;
    private List<ServerResponse> servers;
    private final HttpLauncherClient httpClient;

    public ServersController(WebEngine engine) {
        super(engine);
        httpClient = HttpLauncherClient.getInstance();
        FilesDownloader.onProgress.addListener(this::updateStatus);

        System.out.println("ServersController constructor");

        endpointAddress = "";
        mockedServer = new ServerResponse();
        mockedServer.online = 0;
        mockedServer.version = "1.7.10";
        mockedServer.maxOnline = 20;
        mockedServer.description = "Server #1 for Minecraft";

        servers = fetchServers();
        loadServersOnUI();
    }

    public void loadServersOnUI() {
        System.out.println("Loading Servers");
        String json = Serializer.getSerializer().Serialize(servers);
        Platform.runLater(() -> {
            engine.executeScript("window.renderServers("+json+");");
        });
    }

    public void onPlay(int serverIndex) {
        System.out.println("Server chosen: "+serverIndex);
        ServerResponse server = servers.get(serverIndex);
        Platform.exit();
    }

    public void onEditSettings(int serverIndex) {
        System.out.println("Settings edits for server: "+serverIndex);
        System.out.println("Loading server profile...");
    }

    private void updateStatus(DownloaderInfo info) {
    }

    private void showDownloadingPopup(){}
    private void hideDownloadingPopup(){}

    private List<ServerResponse> fetchServers() {
        boolean isMock = true;
        List<ServerResponse> servers = new ArrayList<>();

        if (isMock) {
            servers.add(mockedServer);
            servers.add(cloneServer(mockedServer, "Server #2 for Minecraft"));
            servers.add(cloneServer(mockedServer,  "Server #3 for Minecraft"));
            servers.add(cloneServer(mockedServer, "Server #4 for Minecraft"));
        }else{
            QueryServersRequest request = new QueryServersRequest();
            request.protocolVersion = 5;
            IPaginatedList<ServerResponse> response = httpClient.sendGetAsync(endpointAddress, request).join();

            servers.addAll(response.getItems());
        }

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
