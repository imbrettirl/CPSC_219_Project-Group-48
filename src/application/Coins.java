package application;

import java.util.Random;

public class Coins {
	private int coins;
	
	Coins(int coinTotal) {
		setCoins(coinTotal);
	}
	
	int getCoins() {
		Random r = new Random();
		int rand = r.nextInt((5 - 1) + 1) + 1;
		coins += rand;
		return coins;
	}
	
	void setCoins(int coins) {
		this.coins = coins;
	}
	
}
