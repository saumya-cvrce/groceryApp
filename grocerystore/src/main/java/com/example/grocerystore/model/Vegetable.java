package com.example.grocerystore.model;

public class Vegetable extends Item {

	int weight;

	

	public Vegetable(String name, double price, int weight) {
		super(name, price);
		this.weight = weight;
	}

	@Override
	public double calculateDiscount() {

		double discountedPrice = 0.0;
		double price = (weight / 100) * pricingConfig.getVegetablePricePer100g();

		if (weight <= 100) {
			discountedPrice = price - (price * pricingConfig.getVegetableDiscount5Percent());
			return Math.round(discountedPrice * 100.0) / 100.0;

		} else if (weight <= 500) {
			discountedPrice = price - (price * pricingConfig.getVegetableDiscount7Percent());
			return Math.round(discountedPrice * 100.0) / 100.0;
		}

		discountedPrice = price - (price * pricingConfig.getVegetableDiscount10Percent());
		return Math.round(discountedPrice * 100.0) / 100.0;
	}

	@Override
	public int getQuantityOrWeight() {
		return weight;
	}

}
