package com.grimmorium.glauncher.contracts.entities;

import java.util.List;

public class ServerUpdatePolicy {
    public String updateFrom;
    public List<String> excludeFromUpdate;
    public List<String> verifyOnUpdate;
}
