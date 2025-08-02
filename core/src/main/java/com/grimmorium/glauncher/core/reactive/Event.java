package com.grimmorium.glauncher.core.reactive;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Event<T> {
    private final List<Consumer<T>> listeners = new ArrayList<>();

    public int addListener(Consumer<T> listener){
        listeners.add(listener);
        return listeners.size() - 1;
    }

    public void removeListener(Consumer<T> listener){
        listeners.remove(listener);
    }

    public void removeListenerByIndex(int listenerIndex){
        listeners.remove(listenerIndex);
    }

    public void removeAllListeners(){
        listeners.clear();
    }

    public void invoke(T value){
        listeners.forEach(listener -> listener.accept(value));
    }
}