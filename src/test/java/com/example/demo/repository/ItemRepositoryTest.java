package com.example.demo.repository;

import com.example.demo.model.response.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void shouldFindAll() {
        List<Item> items = itemRepository.findAll();
        assertEquals(5, items.size());
    }

    @Test
    public void shouldRetrieveItemByName() {
        Optional<Item> banana = itemRepository.findItemByName("Banana");
        assertEquals("Banana", banana.get().getName());
    }

    @Test
    public void shouldGetAllAmount() {
        List<Integer> allAmount = itemRepository.getAllAmount();
        int expectedAmount = 90;

        int actualAmount = allAmount
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    public void shouldGetPrice() {
        Double expectedPrice = 2.23;
        Double actualPrice = itemRepository.getPrice("Apple");

        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void shouldChangePriceForItem() {
        Double expectedPrice = 10.0;
        itemRepository.changePriceForItem(expectedPrice, "Banana");
        Double actualPrice = itemRepository.getPrice("Banana");

        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void shouldReturnTrueIfItemExists() {
        boolean expectedBoolean = true;
        boolean actualBoolean = itemRepository.existsByName("Milk");

        assertEquals(expectedBoolean, actualBoolean);
    }

}