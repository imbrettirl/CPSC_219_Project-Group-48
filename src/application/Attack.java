package application;

public class Attack {
	private int playerDamage;
	private int playerHealth;
	private int enemyHealth;
	private int enemyDamage;
	boolean win = false;
	
	Attack(int pDMG, int eDMG, int pHealth, int eHealth) {
		if (pHealth <=0 || eHealth <= 0) {
			win = true;
		} else {
		setPlayerDamage(pDMG);
		setPlayerHealth(pHealth);
		setEnemyHealth(eHealth);
		setEnemyDamage(eDMG);
		}
	}

	private void setPlayerDamage(int playerDamage) {
		this.playerDamage = playerDamage;
	}

	private void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
	}

	private void setEnemyHealth(int enemyHealth) {
		this.enemyHealth = enemyHealth;
	}

	private void setEnemyDamage(int enemyDamage) {
		this.enemyDamage = enemyDamage;
	}
	

	public int getPlayerDamage() {
		return playerDamage;
	}

	public int getEnemyDamage() {
		return enemyDamage;
	}
	
	int playerAttack() {
		int damageDone = enemyHealth - getPlayerDamage();
		this.enemyHealth = damageDone;
		return damageDone;
	}
	
	int enemyAttack() {
		int damageDone = playerHealth - getEnemyDamage();
		this.playerHealth = damageDone;
		return damageDone;
	}
	
	int energyAttack(int multiplier) {
		int energyDamage = enemyHealth - (3 + multiplier);
		this.enemyHealth = energyDamage;
		return energyDamage;
	}
	int enemyEnergyAttack(int multiplier) {
		int energyDamage = playerHealth - (3+multiplier);
		this.playerHealth = energyDamage;
		return energyDamage;
	}
}
