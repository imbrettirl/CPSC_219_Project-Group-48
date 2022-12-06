package application;
import java.util.ArrayList;
import java.util.Random;

public class Player {
	private int hp;
	private int ep;
	private int damage;
	private int energyDamage = 3;

	private int hpCounter =0;
	private int epCounter=0;
	private int damageCounter=0;

	int[] weapons = new int[3];
	public String[] getPlayersWeapons() {
	//String[] cart;
		
		// look at weapons -> 
	//look at indexes -> fill str
	
		return null;

	}
	
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
}

	public void setWeapons(int[] weapons) {
		this.weapons = weapons;
	}

	public int[] getWeapons() {
		return weapons;
	}

	public void addWeapons(int index, int value) {
		this.weapons[index] = this.weapons[index] + value;
	}

}

