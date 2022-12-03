package application;

import java.util.Random;

public class Player {
	private int hp;
	private int ep;
	private int damage;
	
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
		Random r = new Random();
		int rand = r.nextInt((5 - 1) + 1) + 1;
		damage = rand;
		return damage;
	}

}
