package com.example.demo;

import com.example.demo.model.response.Item;
import com.example.demo.repository.ItemRepository;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(locations = {"classpath:test-configuration.properties"})
class ResTfulItemWebServicesApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private ItemRepository itemRepository;

	@Test
	public void shouldGetItem() throws JSONException {

		when(itemRepository.findById(1002L)).thenReturn(Optional.of(
				new Item("Apple", 2.23, 10)));

		String response = this.restTemplate.getForObject("/items/1002", String.class);

		JSONAssert.assertEquals("{name:Apple,price:2.23,amount:10}", response, false);
	}
}
