package application;

import java.util.Random;

public class XP {
	private int xp;
	
	XP(int exp) {
		setXp(exp);
	}

	int getXp() {
		Random randomXP = new Random();
		int randXP = randomXP.nextInt((20 - 10)+1) + 10;
		xp += randXP;
		return xp;
	}

	void setXp(int xp) {
		this.xp = xp;
	}
}
