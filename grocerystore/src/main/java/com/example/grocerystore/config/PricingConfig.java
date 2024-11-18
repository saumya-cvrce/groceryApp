package com.example.grocerystore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
//Config class which gets the config from app prop file
@Configuration
public class PricingConfig {

    @Value("${bread.price}")
    private double breadPrice;

    @Value("${vegetable.pricePer100g}")
    private double vegetablePricePer100g;

    @Value("${beer.pricePerBottle}")
    private double beerPricePerBottle;

    @Value("${vegetable.discount.5}")
    private double vegetableDiscount5Percent;

    @Value("${vegetable.discount.7}")
    private double vegetableDiscount7Percent;

    @Value("${vegetable.discount.10}")
    private double vegetableDiscount10Percent;

    @Value("${beer.dutchDiscount}")
    private double dutchBeerDiscount;

    @Value("${beer.belgiumDiscount}")
    private double belgiumBeerDiscount;

    @Value("${beer.germanDiscount}")
    private double germanBeerDiscount;
    
    @Value("${beer.box}")
    private int beerBox;

    public int getbeerBox() {
		return beerBox;
	}

	public void setbeerBox(int beerBox) {
		this.beerBox = beerBox;
	}

	public double getBreadPrice() {
        return breadPrice;
    }

    public double getVegetablePricePer100g() {
        return vegetablePricePer100g;
    }

    public double getBeerPricePerBottle() {
        return beerPricePerBottle;
    }

    public double getVegetableDiscount5Percent() {
        return vegetableDiscount5Percent;
    }

    public double getVegetableDiscount7Percent() {
        return vegetableDiscount7Percent;
    }

    public double getVegetableDiscount10Percent() {
        return vegetableDiscount10Percent;
    }

    public double getDutchBeerDiscount() {
        return dutchBeerDiscount;
    }

    public double getBelgiumBeerDiscount() {
        return belgiumBeerDiscount;
    }

    public double getGermanBeerDiscount() {
        return germanBeerDiscount;
    }
}
