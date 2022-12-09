package application;

import java.util.ArrayList;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class MainMenuController {
	private static final int numberOfItems = 0;
	int totalPrice = 0;
	

	Stage applicationStage;
	
	@FXML
    private Button swordButton;
    
    @FXML
    private Button shotgunButton;
    
    @FXML
    private Button axeButton;
	
    @FXML
    private Button ShopButton;

    @FXML
    private Button UpgradesButton;

    @FXML
    private Button StartButton;
    
    @FXML
    private TextField nameTextField;
    
    @FXML
    private Label coinLabel;
    
    @FXML
    private Label xpLabel;

    @FXML
    private TextField item1TextField;
    
    @FXML
    private TextField item2TextField;
    
    @FXML
    private TextField item3TextField;
    
    @FXML
    private Button purchaseButton;
    
    @FXML 
    private ChoiceBox weaponChoiceBox;
    
    Player player = new Player(10, 10);
	Enemy enemy = new Enemy("",10,10);
    
    private Button attackButton;
    private Button specialAttackButton;
	
	boolean sword = false;
    boolean shotgun = false;
    boolean axe = false;
    boolean swordAdded = false;
    boolean shotgunAdded = false;
    boolean axeAdded = false;
    
	Label playerMove = new Label("");
	Label enemyMove = new Label("");
	Label weaponEffect = new Label("");
	
	Label playerHealthLabel = new Label("");
	Label playerEnergyLabel = new Label("");
	Label enemyHealthLabel = new Label("");
	Label enemyEnergyLabel = new Label("");
	
	int coins =175;
	Label coinsEarned = new Label("Coins: " + coins);
	
	int enemyDamage;
	boolean bossVal = false;
	
	int enemyMultiplier = 0;
	
	int xp =200;
	Label xpEarned = new Label("EXP: "+xp);
	
	ChoiceBox<String> itemsChoiceBox = new ChoiceBox<String>();
    @FXML
    void startGame(ActionEvent startGameEvent) {
    	Scene mainScene = applicationStage.getScene();
    	String playerName = nameTextField.getText();
    	
    	coinsEarned.setTextFill(Color.rgb(248, 181, 46));
    	coinsEarned.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    	
    	xpEarned.setTextFill(Color.rgb(114, 215, 52));
    	xpEarned.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    	
    	playerMove.setText("");
    	enemyMove.setText("");
    	weaponEffect = new Label("");
    	bossVal = false;
    	
    	// WINDOW LAYOUT
    	System.out.println("Button Clicked");
    	// main container that contains all elements moving downwards
    	VBox startGameContainer = new VBox(10);
    	// contains all stats, both enemy and player
    	HBox stats = new HBox(75);
    	// contains specific stats for enemy
    	VBox enemyStats = new VBox(10);
    	//contains specific stats for player
    	VBox playerStats = new VBox(10);
    	// next level after stats, contains the first set of options for player
    	HBox options1 = new HBox(50);
    	options1.setPadding(new Insets(30,0,0,25));
    	options1.setAlignment(Pos.CENTER_LEFT);
    	HBox options2 = new HBox(10);
    	options2.setPadding(new Insets(0,0,0,25));
    	options2.setAlignment(Pos.CENTER_LEFT);
    	HBox turnEnd = new HBox(0);
    	turnEnd.setPadding(new Insets(0,0,0,25));
    	VBox moves = new VBox(10);
    	moves.setPadding(new Insets(50,0,0,25));
    	Scene startGameScene = new Scene(startGameContainer, 400, 400);
    	HBox itemBox = new HBox(5);
    	itemBox.setPadding(new Insets(0,0,0,25));
    	
    	// ALL PLAYER/ENEMY STATS (DEFAULT SETUP)
    	player.setHp(10 + (5*player.getHpCounter()));
    	player.setEp(10+ (5*player.getEpCounter()));
    	
    	enemy.setHp(10);
    	enemy.setEp(10);
    	String[] nameArray = {"Bob", "Jack", "Chris", "Brett", "Nika"};
    	Random randomName = new Random();
    	int index = randomName.nextInt(nameArray.length);
    	String ename = nameArray[index];
    	
    	enemy.setName(ename);
    	
    	//SCALING AI CODE
    	// Checking if player has upgraded at ALL and checks the players weapon
    	if (player.getHpCounter() <1 && player.getEpCounter() <1  && player.getDamageCounter() <1 && player.getCurrentWeapon() == 0) {
    		System.out.print("default setup");
    		enemy.setHp(10);
    		enemy.setEp(10);
    	}
    	// if player has upgraded no further than 1 point in each category along with only having the basic sword
    	else if (player.getHpCounter() <=1 && player.getEpCounter() <=1 && player.getDamageCounter() <=1 && player.getCurrentWeapon() <=1){
    		//gives the AI a random stat boost to match the player
    		if (enemy.randomStat() == 1) {
    			enemy.damageUpgrade(2);
    			enemyMultiplier = 1;
    		}
    		else if (enemy.randomStat() == 2) {
    			enemy.energyUpgrade(15);
    		}
    		else {
    			enemy.healthUpgrade(15);
    		}
    	}
    	// if player has upgraded beyond 1 in any category
    	else {
    		// checks which stat has the predominant points put into them, then decides how to buff AI
    		if (player.getHpCounter() > 1 && player.getEpCounter() <=1 && player.getDamageCounter() <=1 && player.getCurrentWeapon() <=1) {
    			System.out.print("bighealthboost");
    			enemy.healthUpgrade(player.getHp()*2);
    			enemy.energyUpgrade(player.getEp()/2);
    			enemy.damageUpgrade(player.getDamage()/2);
    			enemyMultiplier = player.getDamageCounter();
    		}
    		else if (player.getEpCounter() >1 && player.getHpCounter() <=1 && player.getDamageCounter() <=1 && player.getCurrentWeapon() <=1) {
    			System.out.print("bigenergyboost");
    			enemy.energyUpgrade(player.getEp()*2);
    			enemy.healthUpgrade(player.getHp()/2);
    			enemy.damageUpgrade(player.getDamage()/2);
    			enemyMultiplier = player.getDamageCounter();
    		}
    		else if (player.getDamageCounter() >1 && player.getHpCounter() <=1 && player.getEpCounter() <=1 && player.getCurrentWeapon() <=1) {
    			System.out.print("bigattackboost");
    			enemy.damageUpgrade(player.getDamage()*2);
    			enemy.healthUpgrade(player.getHp()/2);
    			enemy.energyUpgrade(player.getEp()/2);
    			enemyMultiplier = player.getDamageCounter()*2;
    		}
    		else {
    			// if all 2 or more upgrades go beyond 1 point (example, 2,2,1) goes to new scaling system, also skips to here if shotgun or axe is being used
    			// also generates random chance to encounter a boss fight
    			if (enemy.bossChance() <5) {
    				System.out.print("bighealthboost");
    				enemy.healthUpgrade(player.getHp()*2);
        			enemy.damageUpgrade(player.getDamage());
        			enemy.energyUpgrade(player.getEp());
        			enemyMultiplier = player.getDamageCounter();
        		}
        		else if (enemy.bossChance() <9) {
        			System.out.print("bigenergyboost");
        			enemy.healthUpgrade(player.getHp());
        			enemy.damageUpgrade(player.getDamage());
        			enemy.energyUpgrade(player.getEp()*2);
        			enemyMultiplier = player.getDamageCounter();
        		}
        		else if (enemy.bossChance() <12){
        			System.out.print("bigattackboost");
        			enemy.healthUpgrade(player.getHp());
        			enemy.damageUpgrade(player.getDamage()*2);
        			enemy.energyUpgrade(player.getEp());
        			enemyMultiplier = player.getDamageCounter()*2;
        		}
        		else {
        			// generates a new enemy with boosted health but lower attack
        			System.out.print("boss fight");
        			Boss boss = new Boss(ename, player.getHp(),10);
        			enemy = boss;
        			boss.bossFight();
        			bossVal = true;
        			
        		}
    		}
    	}
    	
    	// ALL PLAYER/ENEMY STATS DISPLAY
    	Label enemyNameLabel = new Label("Enemy Name: " + enemy.getName());
    	enemyHealthLabel.setText("Enemy Health: " + enemy.getHp());
     	enemyEnergyLabel.setText("Enemy Energy: " + enemy.getEp());
    	Label playerNameLabel = new Label("Player Name: " + playerName);
    	playerHealthLabel.setText("Player Health: " + player.getHp());
    	playerEnergyLabel.setText("Player Energy: " + player.getEp());
    	applicationStage.setTitle("Started Game");
    	
    	// BACK TO MENU
    	Button menuButton = new Button("Back to Menu");
    	menuButton.setOnAction(menuEvent -> applicationStage.setScene(mainScene));
    	
    	//options1
    	this.attackButton = new Button("Attack");
    	attackButton.setTextFill(Color.rgb(168, 29, 29));
    	attackButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    	attackButton.setOnAction(attack -> attackEvent(attack));
    	
    	Label itemsLabel = new Label("Items");
    	itemsLabel.setTextFill(Color.rgb(164, 104, 59));
    	itemsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    	itemsLabel.setPadding(new Insets(5,0,0,0));
    	  
    	// checks what weapons have been purchased and adds to choicebox
    	if (sword == true && swordAdded == false) {
    		swordAdded = true;
    		itemsChoiceBox.getItems().add("Sword");
    	}
    	if (shotgun == true && shotgunAdded == false) {
    		shotgunAdded = true;
    		itemsChoiceBox.getItems().add("Shotgun");
    	}
    	if (axe == true && axeAdded == false) {
    		axeAdded = true;
    		itemsChoiceBox.getItems().add("Axe");
    	}
    	
    	//options2
    	this.specialAttackButton = new Button("Special Attack");
    	specialAttackButton.setTextFill(Color.rgb(31, 216, 215));
    	specialAttackButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    	specialAttackButton.setOnAction(special -> specialAttack(special));
    	
    	Button endTurn = new Button("Do Nothing");
    	endTurn.setTextFill(Color.rgb(101, 101, 101));
    	endTurn.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    	endTurn.setOnAction(nothing -> doNothing(nothing));
    	endTurn.setLayoutX(80);
    	
    	playerMove.setLayoutX(20);
    	enemyMove.setLayoutX(20);
    	
    	// MARGINS
    	VBox.setMargin(enemyNameLabel, new Insets(0,0,0,25));
    	VBox.setMargin(enemyHealthLabel, new Insets(0,0,0,25));
    	VBox.setMargin(enemyEnergyLabel, new Insets(0,0,0,25));

    	VBox.setMargin(playerNameLabel, new Insets(0,0,0,25));
    	VBox.setMargin(playerHealthLabel, new Insets(0,0,0,25));
    	VBox.setMargin(playerEnergyLabel, new Insets(0,0,0,25));
    	
    	// POSITIONING
    	startGameContainer.getChildren().addAll(menuButton, stats, options1, options2, moves);
    	moves.getChildren().addAll(weaponEffect, playerMove, enemyMove, coinsEarned, xpEarned);
    	itemBox.getChildren().addAll(itemsLabel, itemsChoiceBox);
    	options1.getChildren().addAll( attackButton, itemBox);
    	turnEnd.getChildren().addAll(endTurn);
    	options2.getChildren().addAll(specialAttackButton, turnEnd);
    	enemyStats.getChildren().addAll(enemyNameLabel, enemyHealthLabel, enemyEnergyLabel);
    	playerStats.getChildren().addAll(playerNameLabel, playerHealthLabel, playerEnergyLabel);
    	stats.getChildren().addAll(playerStats, enemyStats);
    	applicationStage.setScene(startGameScene);
    	   	
    }

    // Random amount of damage between 1 and 5 dealt to enemy
    void attackEvent(ActionEvent attackEvent) {
    	// checks what item is in the choice box and changes the damage values based on this
    	if (itemsChoiceBox.getValue() == "Sword") {
    		System.out.print("sword added attack");

        	player.weaponBought(1);
    	}
    	else if (itemsChoiceBox.getValue() == "Shotgun") {
    		System.out.print("shotgun added attack");

        	player.weaponBought(2);
    	}
    	else if (itemsChoiceBox.getValue() == "Axe") {
    		System.out.print("axe added attack");
        	player.weaponBought(3);
    	}
    	else {
    		System.out.print("DEFAUTL ATTACK");
    	}
    	// generates a new attack, stores current health, damage of both player and AI
    	Attack pAttack = new Attack(player.getDamage(), enemy.getEnemyDamage(),player.getHp(), enemy.getHp());
    	// checks if player or AI health is already at 0, then checks if the fight is a boss fight
    	if (pAttack.win == false) {
    		if (bossVal == true) {
    			//replaces current default enemy with boss
    			Boss boss = new Boss(enemy.getName(), enemy.getHp(),enemy.getEp());
    			boss.bossFight();
    			enemy = boss;

    		}
    		// does the damage through the attack class
    		enemy.setHp(pAttack.playerAttack());
    		// changes labels
    		enemyHealthLabel.setText("Enemy Health: " + enemy.getHp());
    		if (pAttack.getPlayerDamage() > 0) {
    			// if damage is done, changes label to tell player, also checks if a weapon effect has been used
    	    	playerMove.setText("You did " + pAttack.getPlayerDamage() + " damage");
    	    	// checks the value set in the player class
    	    	if (player.getWeaponEffect() == 1) {
    	    		weaponEffect.setText("Sword struck twice!");
    	    	}
    	    	else if (player.getWeaponEffect() == 2) {
    	    		weaponEffect.setText("Shotgun hit multiple bullets!");
    	    	}
    	    	else if (player.getWeaponEffect() == 3) {
    	    		weaponEffect.setText("Axe did massive damage!");
    	    	}
    	    	else {
    	    		weaponEffect.setText("");
    	    	}
    	    	}
    	    	else {
    	    		playerMove.setText("Your attack missed, you did 0 damage");
    	    	}
    		// if enemy health reaches 0, display winning message and give rewards
    		if (enemy.getHp() <=0 ) {
    			enemyMove.setText("You Won!");
        		playerMove.setText("You did " + pAttack.getPlayerDamage() + " damage");
        		
        		if (bossVal == false) {
        		Coins coinReward = new Coins(coins);
        		coins = coinReward.getCoins();
        		coinsEarned.setText("Coins: " + coins);
        		coinLabel.setText("Coins: "+ coins);
        		
        		XP xpReward = new XP(xp);
        		xp = xpReward.getXp();
        		xpEarned.setText("EXP: "+xp);
        		xpLabel.setText("EXP: "+xp);
        		}
        		// gives extra rewards if boss is defeated
        		else if (bossVal == true) {
        			Coins coinReward = new Coins(coins);
            		coins = coinReward.getCoins()+50;
            		coinsEarned.setText("Coins: " + coins);
            		coinLabel.setText("Coins: "+ coins);
            		
            		XP xpReward = new XP(xp);
            		xp = xpReward.getXp()+100;
            		xpEarned.setText("EXP: "+xp);
            		xpLabel.setText("EXP: "+xp);
        		}
    		}
    		//if enemy doesn't die, it has a 50/50 chance of choosing either regular attack or energy attack
    		//both methods check if player has died, displays appropriate labels
    		else {
    			int choice = enemy.getDecider();
    			if (choice == 1 || enemy.getEp() <5) {
    				player.setHp(pAttack.enemyAttack());
    				playerHealthLabel.setText("Player Health: " + player.getHp());
    				if (pAttack.getEnemyDamage() > 0) {
    					enemyMove.setText("Enemy did " + pAttack.getEnemyDamage() + " damage");
    				} else {
    					enemyMove.setText("Enemy attack missed, 0 damage taken");
    				}
    				if (player.getHp() <=0) {
    					enemyMove.setText("Enemy won!");
    					playerMove.setText("Enemy did " + enemy.getEnergyDamage() + " energy damage");
    					}
    			}
    			else if (choice == 2 && enemy.getEp() >=5) {
    				
    				player.setHp(pAttack.enemyEnergyAttack(enemyMultiplier));
    				playerHealthLabel.setText("Player Health: " + player.getHp());
    				enemy.energyUse();
    				enemyEnergyLabel.setText("Enemy Energy: " + enemy.getEp());
    				enemyMove.setText("Enemy did " + (enemy.getEnergyDamage()+enemyMultiplier) + " energy damage");
    				if (player.getHp() <=0) {
    					enemyMove.setText("Enemy won!");
    					playerMove.setText("Enemy did " + enemy.getEnergyDamage() + " energy damage");
    				}
    			}
    		}    		   		
    	}
    	else {
    		playerMove.setText("Game is over, reset to start a new game");
    		enemyMove.setText("");
    	}
    	System.out.print(bossVal);
    }
    
    // Guaranteed 3 damage attack at the cost of 5 energy, this is not effected by weapons
    void specialAttack(ActionEvent specialAttackEvent) {

    	Attack pAttack = new Attack(player.getDamage(), enemy.getEnemyDamage(),player.getHp(), enemy.getHp());
    	if (pAttack.win == false) {
    		if (bossVal == true) {
    			Boss boss = new Boss(enemy.getName(), enemy.getHp(),enemy.getEp());
    			boss.bossFight();
    			enemy = boss;
    		}
    		if (player.getEp() >= 5) {
    			enemy.setHp(pAttack.energyAttack(player.getDamageCounter()));
    			enemyHealthLabel.setText("Enemy Health: " + enemy.getHp());
    			player.energyUse();
    			playerEnergyLabel.setText("Player Energy: " + player.getEp());
    			playerMove.setText("You did " + (player.getEnergyDamage()+player.getDamageCounter()) + " energy damage");
    			
    			if (enemy.getHp() <= 0) {
    				enemyMove.setText("You Won!");
    	    		playerMove.setText("You did "+ player.getEnergyDamage() + " energy damage");
    				
    	    		if (bossVal == false) {
    	        		Coins coinReward = new Coins(coins);
    	        		coins = coinReward.getCoins();
    	        		coinsEarned.setText("Coins: " + coins);
    	        		coinLabel.setText("Coins: "+ coins);
    	        		
    	        		XP xpReward = new XP(xp);
    	        		xp = xpReward.getXp();
    	        		xpEarned.setText("EXP: "+xp);
    	        		xpLabel.setText("EXP: "+xp);
    	        		}
    	        		else if (bossVal == true) {
    	        			Coins coinReward = new Coins(coins);
    	            		coins = coinReward.getCoins()+50;
    	            		coinsEarned.setText("Coins: " + coins);
    	            		coinLabel.setText("Coins: "+ coins);
    	            		
    	            		XP xpReward = new XP(xp);
    	            		xp = xpReward.getXp()+100;
    	            		xpEarned.setText("EXP: "+xp);
    	            		xpLabel.setText("EXP: "+xp);
    	        		}
    	    	}
    			else {
    				int choice = enemy.getDecider();
        			if (choice == 1 || enemy.getEp() <5) {
        				player.setHp(pAttack.enemyAttack());
        				playerHealthLabel.setText("Player Health: " + player.getHp());
        				if (pAttack.getEnemyDamage() > 0) {
        					enemyMove.setText("Enemy did " + pAttack.getEnemyDamage() + " damage");
        				} else {
        					enemyMove.setText("Enemy attack missed, 0 damage taken");
        				}
        				if (player.getHp() <=0) {
        					enemyMove.setText("Enemy won!");
        					playerMove.setText("Enemy did " + enemy.getEnergyDamage() + " energy damage");
        					}
        			}
        			else if (choice == 2 && enemy.getEp() >=5) {
        				player.setHp(pAttack.enemyEnergyAttack(enemyMultiplier));
        				playerHealthLabel.setText("Player Health: " + player.getHp());
        				enemy.energyUse();
        				enemyEnergyLabel.setText("Enemy Energy: " + enemy.getEp());
        				enemyMove.setText("Enemy did " + (enemy.getEnergyDamage()+enemyMultiplier) + " energy damage");
        				if (player.getHp() <=0) {
        					enemyMove.setText("Enemy won!");
        					playerMove.setText("Enemy did " + (enemy.getEnergyDamage()+enemyMultiplier) + " energy damage");
        				}
        			}
        		} 
    		}
    		else {
    			playerMove.setText("Not enough energy, choose another option");
    			enemyMove.setText("");
    		}
    	}
    	else { playerMove.setText("Game is over, reset to start a new game");
		 enemyMove.setText("");
    	} 
    }
    
    // Do nothing method, skips the players turn and lets the enemy attack
    void doNothing(ActionEvent event) {
    	
		playerMove.setText("");
		Attack pAttack = new Attack(player.getDamage(), enemy.getEnemyDamage(),player.getHp(), enemy.getHp());
		if (pAttack.win == false) {
			if (bossVal == true) {
    			Boss boss = new Boss(enemy.getName(), enemy.getHp(),enemy.getEp());
    			boss.bossFight();
    			enemy = boss;
    		}
			int choice = enemy.getDecider();
			if (choice == 1 || enemy.getEp() <5) {
				player.setHp(pAttack.enemyAttack());
				playerHealthLabel.setText("Player Health: " + player.getHp());
				if (pAttack.getEnemyDamage() > 0) {
					enemyMove.setText("Enemy did " + pAttack.getEnemyDamage() + " damage");
				} else {
					enemyMove.setText("Enemy attack missed, 0 damage taken");
				}
				if (player.getHp() <=0) {
					enemyMove.setText("Enemy won!");
					playerMove.setText("Enemy did " + enemy.getEnemyDamage() + " damage");
					}
			}
			else if (choice == 2 && enemy.getEp() >=5) {
				player.setHp(pAttack.enemyEnergyAttack(enemyMultiplier));
				playerHealthLabel.setText("Player Health: " + player.getHp());
				enemy.energyUse();
				enemyEnergyLabel.setText("Enemy Energy: " + enemy.getEp());
				enemyMove.setText("Enemy did " + (enemy.getEnergyDamage()+enemyMultiplier) + " energy damage");
				if (player.getHp() <=0) {
					enemyMove.setText("Enemy won!");
					playerMove.setText("Enemy did " + (enemy.getEnergyDamage()+enemyMultiplier) + " energy damage");
				}
			}
		} else { playerMove.setText("Game is over, reset to start a new game");
		 enemyMove.setText("");
		}
    }
    Label shopFeedback = new Label("");
    Label coinShop = new Label("Coins: "+coins);
    @FXML
    void goShop(ActionEvent shoppingEvent) {
    	System.out.println("Button Clicked");
    	coinShop.setTextFill(Color.rgb(248, 181, 46));
    	coinShop.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    	coinShop.setPadding(new Insets(7, 0,0,0));
    	Scene mainScene = applicationStage.getScene();
    	//int numberOfWeapons = 3;
    	VBox mainScreenVbox = new VBox(10);  	
    	HBox topHalf = new HBox(30);
    	shopFeedback.setText("");
        coinShop.setText("Coins: "+coins);
    	VBox ItemNumber1VBox = new VBox(10);
    	Label itemNumber1Label  = new Label("Price: 25 coins (Gives a chance to attack twice!)");    	
    	VBox ItemNumber2VBox = new VBox(10);
    	Label itemNumber2Label  = new Label("Price: 50 coins (Gives a chance to attack multiple times!)");
    	VBox ItemNumber3VBox = new VBox(10);
    	Label itemNumber3Label  = new Label("Price: 100 coins (Gives a chance for massive damage!)");
    		
    	Button menuButton = new Button("Back to Menu");
    	topHalf.getChildren().addAll(menuButton, coinShop);
    	mainScreenVbox.getChildren().addAll(topHalf,ItemNumber1VBox, ItemNumber2VBox, ItemNumber3VBox);
    	
    	this.swordButton = new Button("Sword");
    	swordButton.setOnAction(purchaseEvent -> swordPurchased(purchaseEvent));
		
    	this.shotgunButton = new Button("Shotgun");
    	shotgunButton.setOnAction(purchaseEvent -> shotgunPurchased(purchaseEvent));
    	
    	this.axeButton = new Button("Axe");
    	axeButton.setOnAction(purchaseEvent -> axePurchased(purchaseEvent));
  	
    	
    	ItemNumber1VBox.getChildren().addAll(swordButton,itemNumber1Label);
    	ItemNumber2VBox.getChildren().addAll(shotgunButton,itemNumber2Label);
    	ItemNumber3VBox.getChildren().addAll(axeButton,itemNumber3Label, shopFeedback);
    	
    	Scene ShoppingScene = new Scene(mainScreenVbox, 375, 300);
    	applicationStage.setScene(ShoppingScene);
    	menuButton.setOnAction(menuEvent -> applicationStage.setScene(mainScene));
	}

	void swordPurchased(ActionEvent swordEvent) {
		// checks if player has the right amount of coins, and if the item has been purchased already
		if (coins >= 25 && sword == false){
			System.out.println("Sword is added");
			shopFeedback.setText("You bought a Sword!");
			coins = coins - 25;
			coinShop.setText("Coins: "+coins);
			coinLabel.setText("Coins: "+coins);
			coinsEarned.setText("Coins: " + coins);
			// sets value to true to confirm the item is already owned by player
			sword = true;

		}
		else if (sword == true) {
			shopFeedback.setText("You already own this!");
		}else {
			shopFeedback.setText("Sorry! Not enough Coins");
			System.out.println("Not enough money");
		}
	}
	
	void shotgunPurchased(ActionEvent shotgunEvent) {
		if (coins >= 50 && shotgun == false){
			System.out.println("Shotgun is added");
			shopFeedback.setText("You bought a Shotgun!");
			coins = coins - 50;
			coinShop.setText("Coins: "+coins);
			coinLabel.setText("Coins: "+coins);
			coinsEarned.setText("Coins: " + coins);
			shotgun = true;

		}
		else if (shotgun == true) {
			shopFeedback.setText("You already own this!");
		}else {
			shopFeedback.setText("Sorry! Not enough Coins");
			System.out.println("Not enough money");
		}
	}
	

	void axePurchased(ActionEvent axeEvent) {
		if (coins >= 100 && axe == false){
			System.out.println("Axe is added");
			shopFeedback.setText("You bought a Axe!");
			coins = coins - 100;
			coinShop.setText("Coins: "+coins);
			coinLabel.setText("Coins: "+coins);
			coinsEarned.setText("Coins: " + coins);
			axe = true;

		}
		else if (axe == true) {
			shopFeedback.setText("You already own this!");
		}else {
			shopFeedback.setText("Sorry! Not enough Coins");
			System.out.println("Not enough money");
		}
 }
	
	Label xpUpgrade = new Label("EXP: "+ xp);
	Label description = new Label("placeholder");
    @FXML
    void goUpgrades(ActionEvent event) {
    	
    	Scene mainScene = applicationStage.getScene();
    	
    	
    	// WINDOW LAYOUT
    	System.out.println("Button Clicked");
    	VBox mainContainer = new VBox(10);
    	HBox topContainer = new HBox(10);
    	HBox secondaryUpgradeContainer = new HBox(10);
    	HBox cost = new HBox(40);
    	cost.setPadding(new Insets(0,0,0,10));
    	Scene startGameScene = new Scene(mainContainer, 350, 150);
    	
    	Button healthUpgrade = new Button("Increase Health");
    	healthUpgrade.setOnAction(health -> healthIncrease(health));
    	Button damageUpgrade = new Button("Increase Damage");
    	damageUpgrade.setOnAction(damage -> damageIncrease(damage));
    	Button energyUpgrade = new Button("Increase Energy");
    	energyUpgrade.setOnAction(energy -> energyIncrease(energy));
    	
    	Label healthLabel = new Label("Cost: 15 EXP");
    	Label damageLabel = new Label("Cost: 30 EXP");
    	Label energyLabel = new Label("   Cost: 20 EXP");
    	
    	xpUpgrade.setText("EXP: "+ xp);
    	xpUpgrade.setPadding(new Insets(5,0,0,0));
    	description.setText("");
    	
    	// BACK TO MENU
    	Button menuButton = new Button("Back to Menu");
    	menuButton.setOnAction(menuEvent -> applicationStage.setScene(mainScene));    	
    	    	   	
    	// POSITIONING
    	mainContainer.getChildren().addAll(topContainer, secondaryUpgradeContainer, cost, description);
    	topContainer.getChildren().addAll(menuButton, xpUpgrade);
    	cost.getChildren().addAll(healthLabel, energyLabel, damageLabel);
    	secondaryUpgradeContainer.getChildren().addAll(healthUpgrade, energyUpgrade, damageUpgrade);
    	applicationStage.setScene(startGameScene);

    }
    
    void healthIncrease(ActionEvent event) {
    	if (xp >= 15) {
    	xp = xp - 15;
    	xpUpgrade.setText("EXP: "+xp);
    	
    	xpEarned.setText("EXP: "+xp);
		xpLabel.setText("EXP: "+xp);
    	
    	player.healthUpgrade();
    	description.setText("Health has been increased by 5!");
    	}
    	else {
    		description.setText("Not enough EXP");
    	}
    }
    
    void energyIncrease(ActionEvent event) {
    	if (xp >= 20) {
    	xp = xp - 20;
    	xpUpgrade.setText("EXP: "+xp);

    	
    	xpEarned.setText("EXP: "+xp);
		xpLabel.setText("EXP: "+xp);
    	
    	player.energyUpgrade();
    	description.setText("Energy has been increased by 5!");
    	}
    	else {
    		description.setText("Not enough EXP");
    	}
    }
    
    void damageIncrease(ActionEvent event) {
    	if (xp >= 30) {
    	xp = xp -30;
    	xpUpgrade.setText("EXP: "+xp);
    	
    	xpEarned.setText("EXP: "+xp);
		xpLabel.setText("EXP: "+xp);
    	
    	player.attackUpgrade();
    	description.setText("Damage has been increased by 1!");
    	}
    	else {
    		description.setText("Not enough EXP");
    	}
    }
}