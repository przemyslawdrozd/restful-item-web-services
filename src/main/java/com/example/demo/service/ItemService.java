package com.example.demo.service;

import com.example.demo.model.response.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> getItems();

    Optional<Item> getItemById(Long id);

    Optional<Item> getItemByName(String name);

    Optional<Item> createItem(Item item);

    Optional<Item> updateItemById(Long id, Item item);
}
