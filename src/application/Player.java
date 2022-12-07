package application;

import java.util.Random;

public class Player {
	private int hp;
	private int ep;
	private int damage;
	private int energyDamage = 3;
	private int hpCounter =0;
	private int epCounter=0;
	private int damageCounter=0;
	private boolean sword;
	private boolean shotgun;
	private boolean axe;
	private int currentWeapon =0;
	
	Player(int health, int energy) {
		setHp(health);
		setEp(energy);
		hpCounter =0;
		epCounter=0;
		damageCounter=0;
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
		System.out.print("stats: "+getCurrentWeapon());
		System.out.print(axe);
		System.out.print(sword);
		System.out.print(shotgun);
		if (sword == true && getCurrentWeapon() ==1) {
			System.out.print("sword used");
			//sword adds chance to do double damage instead of miss
			Random dodge = new Random();
			int dodgeChance = dodge.nextInt((10-1)+1)+1;
			if (dodgeChance > 1) {
				Random r = new Random();
				int rand = r.nextInt((5-1) + 1) + 1;
				damage = rand + damageCounter;
				return damage;
			}
			else {
				Random r = new Random();
				int rand = r.nextInt((10-2) + 1) + 2;
				damage = rand + damageCounter;
				return damage;
				}
		}
		else if (axe == true && getCurrentWeapon() ==3) {
			System.out.print("axe used");
			//axe replaces dodge chance with massive damage chance
			Random dodge = new Random();
			int dodgeChance = dodge.nextInt((10-1)+1)+1;
			if (dodgeChance > 1) {
				Random r = new Random();
				int rand = r.nextInt((5 - 1) + 1) + 1;
				damage = rand + damageCounter;
				return damage;
			}
			else {
				damage = 20;
				damage = damage + damageCounter;
				return damage;
				}
		}
		else if (shotgun == true && getCurrentWeapon() ==2) {
			System.out.print("shotgun used");
			//shotgun adds chance to triple damage, can still miss
			Random dodge = new Random();
			int dodgeChance = dodge.nextInt((10-1)+1)+1;
			if (dodgeChance < 6) {
				Random r = new Random();
				int rand = r.nextInt((5-1) + 1) + 1;
				damage = rand + damageCounter;
				return damage;
			}
			else if (dodgeChance < 10) {
				Random r = new Random();
				int rand = r.nextInt((15-3) + 1) + 3;
				damage = rand + damageCounter;
				return damage;
			}
			else {
				damage = 0;
				return damage;
				}
		}else {
			System.out.print("default attack");
		Random dodge = new Random();
		int dodgeChance = dodge.nextInt((10-1)+1)+1;
		if (dodgeChance > 1) {
			Random r = new Random();
			int rand = r.nextInt((5 - 1) + 1) + 1;
			damage = rand + damageCounter;
			return damage;
		}
		else {
			damage = 0;
			return damage;
			}
		}		
	}

	int getEnergyDamage() {
		return energyDamage;
	}
	void energyUse() {
		this.ep = this.ep - 5;
	}
	void healthUpgrade() {
		this.hp = this.hp + 5;
		hpCounter = getHpCounter() + 1;
		System.out.print("counter = " +hpCounter);
	}
	void energyUpgrade() {
		this.ep = this.ep + 5;
		epCounter = getEpCounter() + 1;
		System.out.print("counter = " +epCounter);
	}
	void attackUpgrade() {
		this.damage = this.getDamage() + 1;
		damageCounter = getDamageCounter() + 1;
		System.out.print("counter = " +damageCounter);
	}

	int getHpCounter() {
		return hpCounter;
	}

	int getEpCounter() {
		return epCounter;
	}

	int getDamageCounter() {
		return damageCounter;
	}
	public void weaponBought(int name) {
		System.out.print("weaponbought: "+name);
		if (name == 1) {
			sword = true;
			currentWeapon =1;
		}
		else if (name == 2) {
			shotgun = true;
			currentWeapon =2;
		}
		else {
			axe = true;
			currentWeapon =3;
		}
	}

	public int getCurrentWeapon() {
		return currentWeapon;
	}

}
