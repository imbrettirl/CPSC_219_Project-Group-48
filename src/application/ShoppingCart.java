package application;

import java.util.ArrayList;

public class ShoppingCart {
	
private static	ArrayList<Weapon>  cart = new ArrayList<>();

    public int itemCount;   
    public static double totalPrice;
    
    public ShoppingCart() {
    	
    	itemCount = 0;
        totalPrice = 0.0;
    }

    public static void addToCart(Weapon weapon, int quantityEntered) {
    for (int i = 0;i<quantityEntered; i++) {
    		cart.add(weapon);
    		 totalPrice += (weapon.getPrice());
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
    