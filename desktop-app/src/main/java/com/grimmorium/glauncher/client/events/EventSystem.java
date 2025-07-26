package com.grimmorium.glauncher.client.events;

public class EventSystem {
    private static EventSystem instance;

    private EventSystem() {}

    public static EventSystem getInstance(){
        if(instance == null){
            instance = new EventSystem();
        }

        return instance;
    }

    public final Event<Void> onLogin = new Event<>();
}
