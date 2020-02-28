package com.example.demo.controller;

import com.example.demo.model.response.Item;
import com.example.demo.service.ItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemServiceImpl itemService;

    private List<Item> items;

    @BeforeEach
    void beforeAll() {
        this.items = List.of(new Item("Apple", 1.44, 10),
                new Item("Banana", 3.34, 12));
    }

    @Test
    void shouldGetItems() throws Exception {
        when(itemService.getItems()).thenReturn(items);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/items")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{name:Apple,price:1.44,amount:10}," +
                        "{name:Banana,price:3.34,amount:12}]"))
                .andReturn();
    }

    @Test
    void shouldGetItem() throws Exception {
        items.get(0).setId(1L);
        when(itemService.getItemById(1L)).thenReturn(items.get(0));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/items/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{name:Apple,price:1.44,amount:10}"))
                .andReturn();
    }

    @Test
    void shouldGetItemByName() throws Exception {

        when(itemService.getItemByName("Banana")).thenReturn(Optional.of(items.get(1)));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/items/name/Banana")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{name:Banana,price:3.34,amount:12}"))
                .andReturn();
    }

    @Test
    void createItem() {
    }

    @Test
    void updateItem() {
    }

    @Test
    void deleteItem() {
    }

    @Test
    void countItems() {
    }

    @Test
    void discountForItem() {
    }
}