package com.example.grocerystore.model;

import com.example.grocerystore.config.PricingConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/*Base class for different Items */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Bread.class, name = "bread"),
    @JsonSubTypes.Type(value = Vegetable.class, name = "vegetable"),
    @JsonSubTypes.Type(value = Beer.class, name = "beer")
})
public abstract class Item {

	String name;
	double price;
	
	
	protected PricingConfig pricingConfig;
	
	public PricingConfig getPricingConfig() {
		return pricingConfig;
	}


	public void setPricingConfig(PricingConfig pricingConfig) {
		this.pricingConfig = pricingConfig;
	}


	public Item(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
		
	public abstract double calculateDiscount();
	public abstract int getQuantityOrWeight();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
