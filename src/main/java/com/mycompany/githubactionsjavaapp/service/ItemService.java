package com.mycompany.githubactionsjavaapp.service;

import com.mycompany.githubactionsjavaapp.model.Item;

import java.util.List;
import java.util.UUID;

public interface ItemService {
    void addItem(Item item);
    Item getItem(UUID id);
    void updateItem(Item item);
    void removeItem(UUID id);
    List<Item> getItems();
}
