package com.example.grocerystore.model;

public class Bread extends Item {

	private int ageInDays;
	int quantity;

	public Bread(String name, double price, int quantity, int ageInDays) {
		super(name, price);
		this.ageInDays = ageInDays;
		this.quantity = quantity;
	}

	public int getAgeInDays() {
		return ageInDays;
	}

	@Override
	public double calculateDiscount() {

		double cost = pricingConfig.getBreadPrice();
		if (ageInDays <= 1) {
			cost = quantity * cost;
		} else if (ageInDays == 3) {
			cost = Math.ceil(quantity / 2.0) * cost;
		} else if (ageInDays == 6) {
			cost = Math.ceil(quantity / 3.0) * cost;
		} else {
			cost = quantity * cost;
		}
		return cost;

	}

	@Override
	public int getQuantityOrWeight() {
		return quantity;
	}

}
