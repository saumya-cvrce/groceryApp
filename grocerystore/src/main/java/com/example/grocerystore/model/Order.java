package com.example.grocerystore.model;

import java.util.List;

public class Order {

	private List<Item> items;
	
	public Order(List<Item> items) {
        this.items = items;
    }
	
	public List<Item> getItems() {
        return items;
    }
	
	public double calculateTotalPrice() {
        return items.stream().mapToDouble(item -> item.calculateDiscount()).sum();
	}
}