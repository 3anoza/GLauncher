package com.grimmorium.glauncher.client.net.commands.common;

import com.grimmorium.glauncher.client.interfaces.IPaginatedList;

import java.util.ArrayList;
import java.util.List;

public class Pagination<T> implements IPaginatedList<T> {
    private final List<T> items = new ArrayList<>();

    @Override
    public List<T> getItems() {
        return this.items;
    }

    @Override
    public void setItems(List<T> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    @Override
    public int getTotal() {
        return this.items.size();
    }
}
