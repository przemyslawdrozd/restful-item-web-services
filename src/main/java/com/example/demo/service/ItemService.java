package com.example.demo.service;

import com.example.demo.model.response.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> getItems();

    Item getItemById(Long id);

    Optional<Item> getItemByName(String name);

    Optional<Item> createItem(Item item);

    Item updateItem(Long id, Item item);

    boolean deleteItemById(Long id);

    Integer countItems();

    Double discountForItem(String name, Integer percent);
}
