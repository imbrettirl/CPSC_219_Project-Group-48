package application;

import java.util.Random;

public class Player {
	private int hp;
	private int ep;
	private int damage;
	private int energyDamage = 3;
	
	Player(int health, int energy) {
		setHp(health);
		setEp(energy);
	}

	int getHp() {
		return hp;
	}

	void setHp(int hp) {
		this.hp = hp;
	}

	int getEp() {
		return ep;
	}

	void setEp(int ep) {
		this.ep = ep;
	}

	public int getDamage() {
		Random dodge = new Random();
		int dodgeChance = dodge.nextInt((10-1)+1)+1;
		if (dodgeChance > 1) {
			Random r = new Random();
			int rand = r.nextInt((5 - 1) + 1) + 1;
			damage = rand;
			return damage;
		}
		else {
			damage = 0;
			return damage;
		}
		
	}

	int getEnergyDamage() {
		return energyDamage;
	}
	void energyUse() {
		this.ep = this.ep - 5;
	}
}
