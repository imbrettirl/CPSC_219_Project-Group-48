package application;

import java.util.ArrayList;

public class ShoppingCart {
	
private	ArrayList<Weapon>  cart = new ArrayList<>();

    public int itemCount;   
    public double totalPrice;
    
    public ShoppingCart() {
    	
    	itemCount = 0;
        totalPrice = 0.0;
    }

    public void addToCart(Weapon item, int quantityEntered) {
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
    