package application;

import java.util.Random;

public class Weapon {
	
	    public static Object getTotalPrice;
		private String name;
	    private int price;
	    private int WeaponHealthDamage;
		private int WeaponEnergyDamage;
	    private int healthDamage;
		private int energyDamage;
	  
	public Weapon(int price, int healthDamage, int energyDamage) {
			super();
			this.price = price;
			this.healthDamage = healthDamage;
			this.energyDamage = energyDamage;
		}
		
	public int getWeaponHealthDamage() {
		
		return WeaponHealthDamage;
	}

	public int getWeaponHealthDamage(int weaponHealthDamage) {
		
		WeaponHealthDamage = Enemy.getHp() - healthDamage;
		
		return WeaponHealthDamage;
	}

	public int getWeaponEnergyDamage(int WeaponEnergyDamage) {
		
		WeaponEnergyDamage = Enemy.getEp() - energyDamage ;
		
		return WeaponEnergyDamage;
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

	int getEnergyDamage() {
		return energyDamage;
	}
}