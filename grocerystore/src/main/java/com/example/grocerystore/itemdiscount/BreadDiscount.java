package com.example.grocerystore.itemdiscount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.grocerystore.config.PricingConfig;
import com.example.grocerystore.model.Bread;
import com.example.grocerystore.model.Item;

@Component
public class BreadDiscount implements DiscountPolicy{

	@Autowired
	PricingConfig pricingConfig;
	
	@Override
	public double applyDiscount(Item item) {
		Bread bread = (Bread) item;
		bread.setPricingConfig(pricingConfig);
		return bread.calculateDiscount();
	}

}
