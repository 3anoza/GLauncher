package com.grimmorium.glauncher.contracts.interfaces;

import java.util.List;

public interface IPaginatedList<T> {
    List<T> getItems();
    void setItems(List<T> items);

    int getTotal();
}
