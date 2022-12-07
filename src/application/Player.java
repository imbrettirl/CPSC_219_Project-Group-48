package application;
import java.util.Random;

public class Player {
	private int hp;
	private int ep;
	private int damage;
	private int energyDamage = 3;
	private int coins;
	int[] weapons = new int[3];
	
	
	public void printWeapons() {
		for(int i=0; i<3; i++) {
			System.out.print(weapons[i]);
		}
	}
	
	Player(int health, int energy, int coin) {
		setHp(health);
		setEp(energy);
		setCoins(coin);
		this.weapons[0] = 0;
		this.weapons[1] = 0;
		this.weapons[2] = 0;
	}

	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
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

	public String[] getWeapons() {

		int weaponNum = 0;
		
		for (int i=0; i<3; i++) {
			if(weapons[i]>0) {
				weaponNum += 1;
			}
		}
		
		String[] WeaponList = new String[weaponNum];
		
		int j=0;
		
		for (int i=0; i<3; i++) {
			if(weapons[i]>0) {
				
				if (i==0) {
					WeaponList[j] = "Sword";
					j += 1;
					
				}
				if (i==1) {
					WeaponList[j] = "Shotgun";
					j += 1;
				}
				if (i==2) {
					WeaponList[j] = "Axe";
					j += 1;
				}
			}
		}
		
		return WeaponList;
	}

	public int[] addWeapons(int index, int value) {
		this.weapons[index] = this.weapons[index] + value;
		return weapons;
	}

	public void removeWeapon(int index) {
		this.weapons[index] = this.weapons[index] - 1;
		
	}

	public void addCoin(int rand) {
		this.coins += rand;
	}
}