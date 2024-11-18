package com.example.grocerystore.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.grocerystore.config.PricingConfig;
import com.example.grocerystore.model.Beer;
import com.example.grocerystore.model.Bread;
import com.example.grocerystore.model.Item;
import com.example.grocerystore.model.Order;
import com.example.grocerystore.model.Vegetable;
import com.example.grocerystore.service.OrderService;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrderService orderService;

	@MockBean
	private PricingConfig pricingConfig;


	@Test
	public void createOrder_ShouldReturnSuccess() throws Exception {

		when(pricingConfig.getBreadPrice()).thenReturn(1.0);
		when(pricingConfig.getBeerPricePerBottle()).thenReturn(0.5);
		when(pricingConfig.getVegetablePricePer100g()).thenReturn(1.0);

		List<Item> items = Arrays.asList(new Bread("Bread", 1.0, 3, 3), new Vegetable("Vegetable", 1.0, 100),
				new Beer("Beer", 0.5, "Dutch", 6));

		Order mockOrder = new Order(items);
		when(orderService.createOrder(Mockito.anyList())).thenReturn(mockOrder);
		when(orderService.calculateTotalPrice(mockOrder)).thenReturn(5.0);

		String orderRequestJson = "{ \"items\": [ { \"name\": \"Whole Wheat Bread\", \"price\": 1.0, \"type\": \"bread\", \"ageInDays\": 3, \"quantity\": 3 }, { \"name\": \"Vegetables\", \"price\": 1.00, \"type\": \"vegetable\", \"weight\": 200 }, { \"name\": \"Dutch\", \"price\": 0.50, \"type\": \"beer\", \"origin\": \"Dutch\", \"quantity\": 6 } ] }";

		mockMvc.perform(post("/api/order").contentType(MediaType.APPLICATION_JSON).content(orderRequestJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalPrice").value(5.0));
	}
	
	@Test
    public void createOrder_InvalidItemType_ShouldReturnBadRequest() throws Exception {
        String orderRequestJson = "{\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"name\": \"Invalid Item\",\n" +
                "      \"price\": 1.0,\n" +
                "      \"type\": \"invalid\",\n" +
                "      \"quantity\": 3\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        mockMvc.perform(post("/api/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderRequestJson))
                .andExpect(status().isBadRequest());
    }
	
	@Test
    public void createOrder_InternalServerError_ShouldReturnError() throws Exception {
        when(orderService.createOrder(Mockito.anyList())).thenThrow(new RuntimeException("Internal server error"));

        String orderRequestJson = "{\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"name\": \"Bread\",\n" +
                "      \"price\": 1.0,\n" +
                "      \"type\": \"bread\",\n" +
                "      \"quantity\": 3\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        mockMvc.perform(post("/api/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderRequestJson))
                .andExpect(status().isInternalServerError()); 
    }

}
