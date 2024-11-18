package com.example.grocerystore.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.grocerystore.model.Item;
import com.example.grocerystore.model.Order;
import com.example.grocerystore.model.OrderRequest;
import com.example.grocerystore.service.OrderService;

//Controller class to place the order
@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest) {

		try {
			List<Item> items = orderRequest.getItems();
			Order order = orderService.createOrder(items);

			Map<String, Double> itemPrices = items.stream()
					.collect(Collectors.toMap(
							item -> item != null && item.getName().equalsIgnoreCase("vegetable")
									? item.getQuantityOrWeight() + "g " + item.getName()
									: item.getQuantityOrWeight() + " X " + item.getName(),
							orderService::calculateItemPriceWithDiscount));

			double totalPrice = orderService.calculateTotalPrice(order);

			return ResponseEntity.ok(Map.of("itemPrices", itemPrices, "totalPrice", totalPrice));
		} catch (IllegalArgumentException e) {
	        // Handle specific validation related error
	        return ResponseEntity.badRequest().body(Map.of(
	                "error", "Invalid input data",
	                "details", e.getMessage()
	        ));
	    } catch (Exception e) {
	        // Log the error and return a generic error response
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
	                "error", "An unexpected error occurred",
	                "details", e.getMessage()
	        ));
	}
  }
}
