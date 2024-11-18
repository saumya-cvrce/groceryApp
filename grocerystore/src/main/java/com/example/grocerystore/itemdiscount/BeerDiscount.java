package com.example.grocerystore.itemdiscount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.grocerystore.config.PricingConfig;
import com.example.grocerystore.model.Beer;
import com.example.grocerystore.model.Item;

@Component
public class BeerDiscount implements DiscountPolicy{
	
	@Autowired
	PricingConfig pricingConfig;
	
	@Override
	public double applyDiscount(Item item) {

		Beer beer = (Beer) item;
		beer.setPricingConfig(pricingConfig);
		return beer.calculateDiscount();
	}

}
