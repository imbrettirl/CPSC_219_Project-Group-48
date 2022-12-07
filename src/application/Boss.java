package application;

public class Boss extends Enemy{

	Boss(String name, int health, int energy) {
		super(name, health, energy);
		
	}
	void bossFight() {
		super.boss = true;
		super.hp = super.hp + 100;
		super.ep = 0;
		super.name = "BOSS "+getName();
		super.enemyDamage = 1;
	}

}
