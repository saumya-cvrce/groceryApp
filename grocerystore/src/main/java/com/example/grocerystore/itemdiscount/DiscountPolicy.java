package com.example.grocerystore.itemdiscount;

import com.example.grocerystore.model.Item;

//derived items needs to implement this strategy class to apply the discount
public interface DiscountPolicy {
	
	double applyDiscount(Item item);

}
