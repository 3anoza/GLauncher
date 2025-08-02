package com.grimmorium.glauncher.core.services;

import com.grimmorium.glauncher.contracts.entities.DownloaderInfo;
import com.grimmorium.glauncher.core.reactive.Event;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;

public class FilesDownloader {
    public static Event<DownloaderInfo> onProgress;

    public static void download(String fileUrl, String accessToken, String localPath, int bufferSize) throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "multipart/form-data");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        File file = new File(localPath);

        if (file.isDirectory()) {
            if (!file.mkdir()) return;
            localPath = Path.of(localPath, url.getFile()).toString();
        }

        long fileSize = connection.getContentLengthLong();

        InputStream inputStream = connection.getInputStream();
        try (FileOutputStream fileOutputStream = new FileOutputStream(localPath)) {
            byte[] buffer = new byte[Math.max(bufferSize, 1024)];
            int bytesRead;
            long downloadedBytes = 0;
            long startTime = System.currentTimeMillis();
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
                downloadedBytes += bytesRead;
                double elapsedTime = (System.currentTimeMillis() - startTime) / 1000.0;
                double downloadSpeed = elapsedTime > 0 ? downloadedBytes / elapsedTime : 0;
                onProgress.invoke(new DownloaderInfo(downloadedBytes, fileSize, downloadSpeed));
            }
        }

    }
}
