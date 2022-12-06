package application;

import java.util.Random;

public class Weapon {
	
	    public static Object getTotalPrice;
		private String name;
	    private int price;
	    private int WeaponHealthDamage;
		private int WeaponErgyDamage;
	    private int healthDamage;
		private int energyDamage;
	  
	public Weapon(int price, int healthDamage, int energyDamage) {
			super();
			
			this.name = name;
			this.price = price;
			this.healthDamage = healthDamage;
			this.energyDamage = energyDamage;
		}
		
	public int getWeaponHealthDamage() {
		return WeaponHealthDamage;
	}

	public void setWeaponHealthDamage(int weaponHealthDamage) {
		
		WeaponHealthDamage = weaponHealthDamage;
	}

	public int getWeaponErgyDamage() {
		return WeaponErgyDamage;
	}


	public void setWeaponErgyDamage(int weaponErgyDamage) {
		WeaponErgyDamage = weaponErgyDamage;
	}

	public String  getName() {
		return name;
		
	}

	public void setName(String name) {
		this.name = name;
	
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

//	public int getDamage() {
//		Random r = new Random();
//		int rand = r.nextInt((5 - 1) + 1) + 1;
//		healthDamage = rand;
//		return healthDamage;
//	}

	int getEnergyDamage() {
		return energyDamage;
	}


	
}
