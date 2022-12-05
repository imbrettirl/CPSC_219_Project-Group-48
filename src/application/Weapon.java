package application;

public class Weapon {
	
	    private String name;
	    private double price;
	  

	  
	    public Weapon (String itemName, double itemPrice) {
	    	name = itemName;
	    	price = itemPrice;

	    }

	public String  getName() {
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
