package application;

import java.util.Random;

public class Enemy {
	private String name;
	public static int hp;
	public static int ep;
	private int enemyDamage;
	private int energyDamage =3;
	
	Enemy(String name, int health, int energy) {
		this.setName(name);
		setHp(health);
		setEp(energy);
	}

	static int getHp() {
		return hp;
	}

	void setHp(int hp) {
		this.hp = hp;
	}

	static int getEp() {
		return ep;
	}

	void setEp(int ep) {
		this.ep = ep;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	public int getEnemyDamage() {
		Random dodge = new Random();
		int dodgeChance = dodge.nextInt((10-1)+1)+1;
		if (dodgeChance > 1) {
			Random r = new Random();
			int rand = r.nextInt((5 - 1) + 1) + 1;
			enemyDamage = rand;
			return enemyDamage;
		}
		else {
			enemyDamage = 0;
			return enemyDamage;
		}
	}

	int getEnergyDamage() {
		return energyDamage;
	}
}
