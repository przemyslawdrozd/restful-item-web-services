package com.example.demo;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class ResTfulItemWebServicesApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shouldGetItem() throws JSONException {
		String response = this.restTemplate.getForObject("/items/1002", String.class);

		JSONAssert.assertEquals("{name:Apple,price:2.23,amount:10}", response, false);
	}
}
