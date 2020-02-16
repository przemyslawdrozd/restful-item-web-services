package com.example.demo.service;

import com.example.demo.exception.ItemNotFoundException;
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
    public Item getItemById(Long id) {

        return itemRepository.findById(id).orElseThrow(()
                -> new ItemNotFoundException("Item not found: " + id));
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
    public Item updateItem(Long id, Item verifiedItem) {

        return itemRepository.findById(id).map(item -> {
            item.setName(verifiedItem.getName());
            item.setAmount(verifiedItem.getAmount());
            item.setPrice(verifiedItem.getPrice());
            return itemRepository.save(item);
        })
            .orElseThrow( () -> new ItemNotFoundException("Item not found: " + id));
    }

    @Override
    public boolean deleteItemById(Long id) {

        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        } else {
            LOG.warn("Item id: {} does not exist", id);
            return false;
        }
    }

    @Override
    public Integer countItems() {

        return itemRepository.getAllAmount()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public Double discountForItem(String name, Integer percent) {

        if (itemRepository.existsByName(name)){
            Double price = itemRepository.getPrice(name);
            price -= (price * percent) / 100;
            itemRepository.changePriceForItem(price, name);
            return price;
        } else {
            throw new ItemNotFoundException("Item not found: " + name);
        }
    }
}