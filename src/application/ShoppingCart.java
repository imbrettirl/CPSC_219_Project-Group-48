package application;

import java.util.ArrayList;

public class ShoppingCart {
	
private	ArrayList<Item> cart = new ArrayList<>();

    private int itemCount;   
    private double totalPrice;
    
    public ShoppingCart() {
    	
    	itemCount = 0;
        totalPrice = 0.0;
    }

    public void addToCart(Item item, int quantityEntered) {
    for (int i = 0;i<quantityEntered; i++) {
    		cart.add(item);
    		 totalPrice += (item.getPrice());
    	}

    }

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
    
    
}
    