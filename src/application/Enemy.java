package application;

import java.util.Random;

public class Enemy {
	protected String name;
	protected int hp;
	protected int ep;
	protected int enemyDamage;
	protected int energyDamage =3;
	private int decider;
	protected boolean boss = false;
	
	Enemy(String name, int health, int energy) {
		setName(name);
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

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	public int getEnemyDamage() {
		if (boss == false) {
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
		else {
			Random dodge = new Random();
			int dodgeChance = dodge.nextInt((2-0)+1)+0;
			if (dodgeChance <=1) {
				return enemyDamage;
			}
			else {
				enemyDamage = 0;
				return enemyDamage;
				}
		}
	}

	int getEnergyDamage() {
		return energyDamage;
	}
	int getDecider() {
		Random r = new Random();
		int rand = r.nextInt((2 - 1) + 1) + 1;
		return rand;
	}
	
	void energyUse() {
		this.ep = this.ep -5;
	}
	void healthUpgrade(int health) {
		System.out.print("health upgraded by "+ health);
		this.hp = health;
	}
	void energyUpgrade(int energy) {
		System.out.print("energy upgraded by "+ energy);
		this.ep = energy;
	}
	void damageUpgrade(int damage) {
		System.out.print("damage upgraded by "+ damage);
		this.enemyDamage = damage; 
	}
	int randomStat() {
		Random r = new Random();
		int rand = r.nextInt((4 - 1) + 1) + 1;
		return rand;
	}
	int bossChance() {
		Random r = new Random();
		int rand = r.nextInt((12 - 1) + 1) + 1;
		return rand;
	}
	
}
