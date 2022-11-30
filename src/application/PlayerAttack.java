package application;

public class PlayerAttack {
	private String attack;
	private String specialAttack;
	
	PlayerAttack(String atk, String spcAtk) {
		setAttack(atk);
		setSpecialAttack(spcAtk);
	}

	int getAttack() {
		if (attack == "Basic Attack") {
			return 10;
		}
		return 0;
	}

	void setAttack(String attack) {
		this.attack = attack;
	}

	String getSpecialAttack() {
		return specialAttack;
	}

	void setSpecialAttack(String specialAttack) {
		this.specialAttack = specialAttack;
	}

}
