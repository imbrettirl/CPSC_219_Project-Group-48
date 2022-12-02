package application;

public class Player {
	private int hp;
	private int ep;
	public int damage = 1;
	
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

}
