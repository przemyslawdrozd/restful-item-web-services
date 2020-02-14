package com.example.demo.controller;

import com.example.demo.exception.CreateItemException;
import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.model.response.Item;
import com.example.demo.service.ItemService;
import com.example.demo.service.ItemServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> getItems() {

        List<Item> items = itemService.getItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable("id") final Long id) {
        Optional<Item> oItem = itemService.getItemById(id);

        Item item = oItem.orElseThrow(()
                -> new ItemNotFoundException("Item not found: " + id));

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Item> getItemByName(@PathVariable("name") final String name) {

        Optional<Item> oItem = itemService.getItemByName(name);

        Item item = oItem.orElseThrow(() -> new ItemNotFoundException("Item not found: " + name));

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createItem(@RequestBody Item newItem) {

        Item verifiedItem = Optional.of(newItem)
                .filter(i -> i.getName() != null)
                .filter(i -> i.getAmount() < 0)
                .filter(i -> i.getPrice() < 0)
                .orElseThrow(() -> new CreateItemException("Valid item data"));

        itemService.createItem(verifiedItem);
        return new ResponseEntity<>("New Item Created", HttpStatus.OK);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteItem(@PathVariable("id") Long id) {
//
//        // Logic to delete
//        return ResponseEntity.noContent().build();
//    }
}
