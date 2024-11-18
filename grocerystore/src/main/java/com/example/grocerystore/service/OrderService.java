package com.example.grocerystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.grocerystore.config.PricingConfig;
import com.example.grocerystore.model.Item;
import com.example.grocerystore.model.Order;

//Order service to create a order and calaulate price
@Service
public class OrderService {

	@Autowired
	PricingConfig pricingConfig;
	
	public Order createOrder(List<Item> items) {
		
		for(Item item:items)
		{
			item.setPricingConfig(pricingConfig);
		}
		return new Order(items);
	}
	
	public double calculateItemPriceWithDiscount(Item item) {
        return item.calculateDiscount();
    }

    public double calculateTotalPrice(Order order) {
        return order.calculateTotalPrice();
    }
}
