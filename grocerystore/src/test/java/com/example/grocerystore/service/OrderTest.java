package com.example.grocerystore.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.grocerystore.model.Item;
import com.example.grocerystore.model.Order;

import java.util.List;

public class OrderTest {

    private Item breadItem;
    private Item vegetableItem;
    private Item beerItem;

    @BeforeEach
    public void setup() {
        breadItem = mock(Item.class);
        vegetableItem = mock(Item.class);
        beerItem = mock(Item.class);

        when(breadItem.getName()).thenReturn("Bread");
        when(breadItem.getPrice()).thenReturn(1.00);
        when(breadItem.calculateDiscount()).thenReturn(1.00);  
        when(breadItem.getQuantityOrWeight()).thenReturn(2);

        when(vegetableItem.getName()).thenReturn("Vegetable");
        when(vegetableItem.getPrice()).thenReturn(2.00);
        when(vegetableItem.calculateDiscount()).thenReturn(3.00); 
        when(vegetableItem.getQuantityOrWeight()).thenReturn(200);

        when(beerItem.getName()).thenReturn("Beer");
        when(beerItem.getPrice()).thenReturn(1.50);
        when(beerItem.calculateDiscount()).thenReturn(4.50); 
        when(beerItem.getQuantityOrWeight()).thenReturn(6);
    }

    @Test
    public void testOrderPriceCalculation() {
        List<Item> items = List.of(breadItem, vegetableItem, beerItem);
        Order order = new Order(items);

        double totalPrice = order.calculateTotalPrice();

        assertEquals(8.50, totalPrice, "Total price should be 8.50");
    }
  
}
