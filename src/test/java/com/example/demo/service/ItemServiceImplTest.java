package com.example.demo.service;

import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.model.response.Item;
import com.example.demo.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceImplTest {

    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void getItems() {
        List<Item> items = List.of(
                new Item("Apple", 1.44, 10),
                new Item("Banana", 3.34, 12)
        );

        when(itemRepository.findAll()).thenReturn(items);
        List<Item> actualItems = itemService.getItems();
        assertEquals(10, actualItems.get(0).getAmount().intValue());
    }

    @Test
    public void getItemById() {
        Item item = new Item("Apple", 1.44, 10);
        item.setId(1001L);

        when(itemRepository.findById(1001L)).thenReturn(Optional.of(item));
        Item expectedItem = itemService.getItemById(1001L);
        assertEquals(item, expectedItem);
    }

    @Test
    public void shouldDiscountTenPercent() {
        double expectedDiscount = 9.0;

        when(itemRepository.existsByName("name")).thenReturn(true);
        when(itemRepository.getPrice("name")).thenReturn(10.0);

        Double actualDiscount = itemService.discountForItem("name", 10);
        assertEquals(expectedDiscount, actualDiscount, 1);
    }

    @Test(expected = ItemNotFoundException.class)
    public void shouldThrowItemNodFoundForDiscount() {
        when(itemRepository.existsByName("name")).thenReturn(false);
        itemService.discountForItem("name", 10);
    }

    @Test
    public void getItemByName() {
    }

    @Test
    public void createItem() {
    }

    @Test
    public void updateItem() {
    }

    @Test
    public void deleteItemById() {
    }

    @Test
    public void countItems() {
    }
}