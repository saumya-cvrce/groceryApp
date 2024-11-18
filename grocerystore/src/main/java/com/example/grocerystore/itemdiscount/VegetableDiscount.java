package com.example.grocerystore.itemdiscount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.grocerystore.config.PricingConfig;
import com.example.grocerystore.model.Item;
import com.example.grocerystore.model.Vegetable;

@Component
public class VegetableDiscount implements DiscountPolicy{

	
	@Autowired
	PricingConfig pricingConfig;
	
	@Override
	public double applyDiscount(Item item) {
		Vegetable veg = (Vegetable) item;
		veg.setPricingConfig(pricingConfig);
		return veg.calculateDiscount();
	}

}
