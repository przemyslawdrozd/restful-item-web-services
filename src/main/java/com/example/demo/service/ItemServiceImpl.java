package com.example.demo.service;

import com.example.demo.model.response.Item;
import com.example.demo.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @Override
    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Optional<Item> getItemByName(String name) {

        return itemRepository.findItemByName(name);
    }

    @Override
    public Optional<Item> createItem(Item item) {

        Item savedItem = itemRepository.save(item);

        return Optional.of(savedItem);
    }

    @Override
    public Optional<Item> updateItemById(Long id, Item item) {
        return Optional.empty();
    }
}