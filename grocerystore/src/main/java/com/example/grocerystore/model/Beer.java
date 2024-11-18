package com.example.grocerystore.model;

public class Beer extends Item {

	String origin;
	int quantity;

	public Beer(String name, double price, String origin, int quantity) {
		super(name, price);
		this.origin = origin;
		this.quantity = quantity;

	}
	
	

	@Override
	public double calculateDiscount() {

		double price = quantity * pricingConfig.getBeerPricePerBottle();
		int bearBox = pricingConfig.getbeerBox();
		if (quantity >= bearBox) {

			switch (origin) {
			case "Dutch":
				return (quantity / bearBox) * pricingConfig.getDutchBeerDiscount();
			case "Belgium":
				return (quantity / bearBox) * pricingConfig.getBelgiumBeerDiscount();
			case "German":
				return (quantity / bearBox) * pricingConfig.getGermanBeerDiscount();
			}
		}
		return price;
	}



	@Override
	public int getQuantityOrWeight() {
		return quantity;
	}

}
