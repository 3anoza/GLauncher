package com.grimmorium.glauncher.contracts.entities;

public class DownloaderInfo {
    public long downloadedBytes;
    public long totalBytes;
    public double averageBytesPerSec;

    public DownloaderInfo(long downloadedBytes, long totalBytes, double averageBytesPerSec) {
        this.downloadedBytes = downloadedBytes;
        this.totalBytes = totalBytes;
        this.averageBytesPerSec = averageBytesPerSec;
    }
}
