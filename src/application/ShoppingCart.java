package application;

import java.util.ArrayList;

public class ShoppingCart {
	
public static	ArrayList<Weapon>cart = new ArrayList<>();

public int itemCount;   
public static int totalPrice;

public ShoppingCart() {
	
	itemCount = 0;
    totalPrice = 0;
}

public int addToCart(Weapon weapon) {
for (int i = 0;; i++) {
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

public void setTotalPrice(int totalPrice) {
	this.totalPrice = totalPrice;
}


}

	
