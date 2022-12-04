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
import javafx.stage.Stage;
import javafx.scene.Scene;

public class MainMenuController {
	private static final int numberOfItems = 0;

	Stage applicationStage;

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
    
    
    Player player = new Player(10, 10);
	Enemy enemy = new Enemy("",10,10);
    
    private Button attackButton;
    private Button specialAttackButton;
	
	
	Label playerMove = new Label("");
	Label enemyMove = new Label("");
	
	Label playerHealthLabel = new Label("");
	Label playerEnergyLabel = new Label("");
	Label enemyHealthLabel = new Label("");
	Label enemyEnergyLabel = new Label("");
	
	int coins =0;
	Label coinsEarned = new Label("Coins: " + coins);
	
	int healthMultiplier;
	int energyMultiplier;
	int damageMultiplier;
	int enemyDamage;
	boolean bossVal = false;
	
	int xp =0;
	ExperiencePoints experience = new ExperiencePoints(xp);
	Label xpEarned = new Label("EXP: "+xp);
	
    @FXML
    void startGame(ActionEvent startGameEvent) {
    	Scene mainScene = applicationStage.getScene();
    	
    	String playerName = nameTextField.getText();
    	
    	playerMove.setText("");
    	enemyMove.setText("");
    	
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
    	HBox options1 = new HBox(10);
    	options1.setPadding(new Insets(30,0,0,25));
    	options1.setAlignment(Pos.CENTER_LEFT);
    	HBox options2 = new HBox(10);
    	options2.setPadding(new Insets(0,0,0,25));
    	options2.setAlignment(Pos.CENTER_LEFT);
    	HBox turnEnd = new HBox(0);
    	turnEnd.setPadding(new Insets(0,0,0,25));
    	VBox moves = new VBox(20);
    	moves.setPadding(new Insets(50,0,0,25));
    	Scene startGameScene = new Scene(startGameContainer, 400, 400);
    	HBox itemBox = new HBox(5);
    	itemBox.setPadding(new Insets(0,0,0,25));
    	
    	// ALL PLAYER/ENEMY STATS 
    	player.setHp(10 + (5 * healthMultiplier));
    	player.setEp(10 + (5 * energyMultiplier));
    	String[] nameArray = {"Bob", "Jack", "Chris", "Brett", "Nika"};
    	Random randomName = new Random();
    	int index = randomName.nextInt(nameArray.length);
    	String ename = nameArray[index];
    	
    	enemy.setName(ename);
    	
    	// Checking if player has upgraded at ALL
    	if (healthMultiplier == 0 && energyMultiplier == 0 && damageMultiplier == 0) {
    		System.out.print("default setup");
    		enemy.setHp(10);
    		enemy.setEp(10);
    	}
    	else if (healthMultiplier <= 1 && energyMultiplier <= 1 && damageMultiplier <= 1){
    		Random rng = new Random();
    		int eu = rng.nextInt((3-1)+1)+1;
    		if (eu == 1) {
    			System.out.print("enemyhealthupgraded");
    			enemy.setHp(10 + (5 * 1));
    			enemy.setEp(10);
    		}
    		else if (eu == 2) {
    			System.out.print("enemyenergyupgraded");
    			enemy.setEp(10 + (5 * 1));
    			enemy.setHp(10);
    		}
    		else if (eu == 3) {
    			System.out.print("enemydamageupgraded");
    			enemy.setHp(10);
    			enemy.setEp(10);
    			enemyDamage = 1;
    		}
    	}
    	// Picks a random stat to boost
    	else {
    		Random rng = new Random();
    		int eu = rng.nextInt((3-1)+1)+1;
    		if (eu == 1) {
    			System.out.print("bighealthboost");
    			enemy.setHp(10 + (10 * damageMultiplier));
    			enemy.setEp(10 + (5 * energyMultiplier));
    			enemyDamage = healthMultiplier /2;
    		}
    		else if (eu == 2) {
    			Random boss = new Random();
    			int bossn = boss.nextInt((10-1)+1)+1;
    			if (bossn < 10) {
    			System.out.print("bigenergyboost");
    			enemy.setEp(10 + (10 * energyMultiplier));
    			enemy.setHp(10+ (5 * damageMultiplier));
    			enemyDamage = healthMultiplier;
    			}
    			else if (bossn == 10) {
    				enemy.setName("BOSS");
    				enemy.setHp(100);
    				enemy.setEp(20);
    				enemyDamage = 0;
    				bossVal = true;
    			}
    		}
    		else if (eu == 3) {
    			System.out.print("bigattackboost");
    			enemy.setHp(10+ (2 * damageMultiplier));
    			enemy.setEp(10+ (3 * energyMultiplier));
    			enemyDamage = healthMultiplier * 2;
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
    	attackButton.setOnAction(attack -> attackEvent(attack));
    	
    	Label itemsLabel = new Label("Items");
    	itemsLabel.setPadding(new Insets(5,0,0,0));
    	ChoiceBox<String> itemsChoiceBox = new ChoiceBox<String>();
    	Button itemsButton = new Button("Use Item");
    	
    	//options2
    	this.specialAttackButton = new Button("Special Attack");
    	specialAttackButton.setOnAction(special -> specialAttack(special));
    	
    	Button endTurn = new Button("Do Nothing");
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
    	moves.getChildren().addAll(playerMove, enemyMove, coinsEarned, xpEarned);
    	itemBox.getChildren().addAll(itemsLabel, itemsChoiceBox, itemsButton);
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
    	
    	// Checks to make sure player and enemy aren't dead, otherwise game has ended.
    	if (player.getHp() > 0 && enemy.getHp() > 0) {
    	
    	// Player gets to go first, random number is generated as damage and dealt to enemy.
    	int damageTaken = enemy.getHp() - (player.getDamage() + damageMultiplier);
    	int damageDone = enemy.getHp() - damageTaken;
    	enemy.setHp(damageTaken);
    	enemyHealthLabel.setText("Enemy Health: " + enemy.getHp());
    	if (damageDone > 0) {
    	playerMove.setText("You did " + damageDone + " damage");
    	}
    	else {
    		playerMove.setText("Your attack missed, you did 0 damage");
    	}
    	
    	// If enemy dies, win game and coins
    	if (enemy.getHp() <= 0) {
    		if (bossVal == false) {
    		enemyMove.setText("You Won!");
    		playerMove.setText("You did " + damageDone + " damage");
    		Random r = new Random();
    		int rand = r.nextInt((5 - 1) + 1) + 1;
    		coins += rand;
    		coinsEarned.setText("Coins: " + coins);
    		coinLabel.setText("Coins: "+ coins);
    		Random randomXP = new Random();
    		int randXP = randomXP.nextInt((20 - 10)+1) + 10;
    		System.out.print("random = "+randXP);
    		xp += randXP;
    		System.out.print("xp ="+xp);
    		experience.setXp(xp);
    		xpEarned.setText("EXP: "+experience.getXp());
    		xpLabel.setText("EXP: "+experience.getXp());
    		}
    		else if (bossVal == true) {
    			enemyMove.setText("You Beat a Boss!!!");
        		playerMove.setText("You did " + damageDone + " damage");
        		
        		coins += 50;
        		coinsEarned.setText("Coins: " + coins);
        		coinLabel.setText("Coins: "+ coins);
        		
        		xp += 100;
        		experience.setXp(xp);
        		xpEarned.setText("EXP: "+experience.getXp());
        		xpLabel.setText("EXP: "+experience.getXp());
    		}
    	}
    	else {
    		// enemies turn now, generates random value to decide enemies move
    		Random r = new Random();
    		int rand = r.nextInt((2 - 1) + 1) + 1;
    		
    		// regular attack, random damage between 1-5
    		if (rand == 1) {
    			if (bossVal == false) {
    				int damageEnemy = player.getHp() - (enemy.getEnemyDamage() + enemyDamage);
    				int enemyDamageDone = player.getHp() - damageEnemy;
    				player.setHp(damageEnemy);
    				playerHealthLabel.setText("Player Health: " + player.getHp());
    				if (enemyDamageDone > 0) {
    					enemyMove.setText("Enemy did " + enemyDamageDone + " damage");
    				} else {
    					enemyMove.setText("Enemy attack missed, 0 damage taken");

    				}
    	
    				if (player.getHp() <=0) {
    					enemyMove.setText("Enemy won!");
    					playerMove.setText("Enemy did " + enemyDamageDone + " damage");
    					}
    				}
    			else if (bossVal == true) {
    				Random random = new Random();
    				int bossDmg = random.nextInt((1-0)+1)+0;
    				int damageEnemy = player.getHp() - (bossDmg);
    				int enemyDamageDone = player.getHp() - damageEnemy;
    				player.setHp(damageEnemy);
    				playerHealthLabel.setText("Player Health: " + player.getHp());
    				if (enemyDamageDone > 0) {
    					enemyMove.setText("Boss did " + enemyDamageDone + " damage");
    				} else {
    					enemyMove.setText("Boss attack missed, 0 damage taken");

    				}
    	
    				if (player.getHp() <=0) {
    					enemyMove.setText("The Boss has won!");
    					playerMove.setText("Boss did " + enemyDamageDone + " damage");
    					}
    			}
    		}
    		// energy attack, guaranteed 3 damage at the cost of 5 energy
    		else if (rand == 2 && enemy.getEp() >= 5){
    			System.out.print(bossVal);
    			if (bossVal == false) {
    				int energyDamage = player.getHp() - (enemy.getEnergyDamage()+enemyDamage);
    				int energyDamageDone = player.getHp() - energyDamage;
    				int energyUsed = enemy.getEp() - 5;
    				enemy.setEp(energyUsed);
    				enemyEnergyLabel.setText("Enemy Energy: " + enemy.getEp());
    				player.setHp(energyDamage);
    				playerHealthLabel.setText("Player Health: " + player.getHp());
    				enemyMove.setText("Enemy did " + energyDamageDone + " energy damage");
        		
    				if (player.getHp() <=0) {
    					enemyMove.setText("Enemy won!");
    					playerMove.setText("Enemy did " + energyDamageDone + " energy damage");
    					}
    				}
    			else if (bossVal == true) {
    				System.out.print(bossVal);
    				int energyDamage = player.getHp() - (1);
    				int energyDamageDone = player.getHp() - energyDamage;
    				int energyUsed = enemy.getEp() - 5;
    				enemy.setEp(energyUsed);
    				enemyEnergyLabel.setText("Enemy Energy: " + enemy.getEp());
    				player.setHp(energyDamage);
    				playerHealthLabel.setText("Player Health: " + player.getHp());
    				enemyMove.setText("The Boss did " + energyDamageDone + " energy damage");
        		
    				if (player.getHp() <=0) {
    					enemyMove.setText("The Boss won!");
    					playerMove.setText("The Boss did " + energyDamageDone + " energy damage");
    					}
    				}
    			}
    		}
    	}
    	else { playerMove.setText("Game is over, reset to start a new game");
    		 enemyMove.setText("");
    	}
    }
    
    // Guaranteed 3 damage attack at the cost of 5 energy
    void specialAttack(ActionEvent specialAttackEvent) {
    	
    	if (player.getHp() > 0 && enemy.getHp() > 0) {
    		
    		if (player.getEp() >= 5) {
    			
    			int damageDone = enemy.getHp() - (player.getEnergyDamage() + damageMultiplier);
    			int damageDelt = enemy.getHp() - damageDone;
    			enemy.setHp(damageDone);
    			int energyUsed = player.getEp() - 5;
    			player.setEp(energyUsed);
    			enemyHealthLabel.setText("Enemy Health: " + enemy.getHp());
    			playerEnergyLabel.setText("Player Energy: " + player.getEp());
    	    	playerMove.setText("You did " + damageDelt + " energy damage");
    	    	
    	    	if (enemy.getHp() <= 0) {
    	    		if (bossVal == false) {
    	    		enemyMove.setText("You Won!");
    	    		playerMove.setText("You did "+ damageDelt + " energy damage");
    	    		Random r = new Random();
    	    		int rand = r.nextInt((5 - 1) + 1) + 1;
    	    		coins += rand;
    	    		coinsEarned.setText("Coins: " + coins);
    	    		coinLabel.setText("Coins: "+ coins);
    	    		Random randomXP = new Random();
    	    		int randXP = randomXP.nextInt((20 - 10)+1) + 10;
    	    		xp += randXP;
    	    		experience.setXp(xp);
    	    		xpEarned.setText("EXP: "+experience.getXp());
    	    		xpLabel.setText("EXP: "+experience.getXp());
    	    		}
    	    		else if (bossVal == true) {
    	    			enemyMove.setText("You Defeated the Boss!!!");
        	    		playerMove.setText("You did "+ damageDelt + " energy damage");
        	    		
        	    		coins += 50;
        	    		coinsEarned.setText("Coins: " + coins);
        	    		coinLabel.setText("Coins: "+ coins);

        	    		xp += 100;
        	    		experience.setXp(xp);
        	    		xpEarned.setText("EXP: "+experience.getXp());
        	    		xpLabel.setText("EXP: "+experience.getXp());
    	    		}
    	    	}
    	    	else {
    	    		Random r = new Random();
    	    		int rand = r.nextInt((2 - 1) + 1) + 1;
    	    		
    	    		if (rand == 1) {
    	    			if (bossVal == false)  {
    	    				int damageEnemy = player.getHp() - (enemy.getEnemyDamage()+enemyDamage);
    	    				int enemyDamageDone = player.getHp() - damageEnemy;
    	    				player.setHp(damageEnemy);
    	    				playerHealthLabel.setText("Player Health: " + player.getHp());
    	    				if (enemyDamageDone > 0) {
    	    					enemyMove.setText("Enemy did " + enemyDamageDone + " damage");
    	    				} 
    	    				else {
    	    					enemyMove.setText("Enemy attack missed, 0 damage taken");
    	    				}
    	    	
    	    				if (player.getHp() <=0) {
    	    					enemyMove.setText("Enemy won!");
    	    					playerMove.setText("Enemy did " + enemyDamageDone + " damage");
    	    					}
    	    				}
    	    			else if (bossVal == true) {
    	    				Random random = new Random();
    	    				int bossDmg = random.nextInt((1-0)+1)+0;
    	    				int damageEnemy = player.getHp() - (bossDmg);
    	    				int enemyDamageDone = player.getHp() - damageEnemy;
    	    				player.setHp(damageEnemy);
    	    				playerHealthLabel.setText("Player Health: " + player.getHp());
    	    				if (enemyDamageDone > 0) {
    	    					enemyMove.setText("Enemy did " + enemyDamageDone + " damage");
    	    				} else {
    	    					enemyMove.setText("Enemy attack missed, 0 damage taken");

    	    				}
    	    	
    	    				if (player.getHp() <=0) {
    	    					enemyMove.setText("The Boss has won!");
    	    					playerMove.setText("Boss did " + enemyDamageDone + " damage");
    	    					}
    	    			}
    	    		}
    	    		else if (rand == 2 && enemy.getEp() >= 5){
    	    			if (bossVal == false) {
    	    			int energyDamage = player.getHp() - (enemy.getEnergyDamage()+enemyDamage);
    	    			int energyDamageDone = player.getHp() - energyDamage;
    	    			int enemyEnergyUsed = enemy.getEp() - 5;
    	    			enemy.setEp(enemyEnergyUsed);
    	    			enemyEnergyLabel.setText("Enemy Energy: " + enemy.getEp());
    	    			player.setHp(energyDamage);
    	    			playerHealthLabel.setText("Player Health: " + player.getHp());
    	        		enemyMove.setText("Enemy did " + energyDamageDone + " energy damage");
    	        		
    	        		if (player.getHp() <=0) {
    	    				enemyMove.setText("Enemy won!");
    	    				playerMove.setText("Enemy did " + energyDamageDone + " energy damage");
    	    				}
    	    		}
    	    			else if (bossVal == true) {
    	    				int energyDamage = player.getHp() - (1);
    	    				int energyDamageDone = player.getHp() - energyDamage;
    	    				int energyUsedBoss = enemy.getEp() - 5;
    	    				enemy.setEp(energyUsed);
    	    				enemyEnergyLabel.setText("Enemy Energy: " + enemy.getEp());
    	    				player.setHp(energyDamage);
    	    				playerHealthLabel.setText("Player Health: " + player.getHp());
    	    				enemyMove.setText("The Boss did " + energyDamageDone + " energy damage");
    	        		
    	    				if (player.getHp() <=0) {
    	    					enemyMove.setText("The Boss won!");
    	    					playerMove.setText("The Boss did " + energyDamageDone + " energy damage");
    	    					}
    	    			}
    	    			}
    	    		}
    		}
    		else {
    			playerMove.setText("Not enough energy, choose another option");
    		}
    	}
    	else { playerMove.setText("Game is over, reset to start a new game");
		 enemyMove.setText("");
    	} 
    }
    
    // Do nothing method, skips the players turn and lets the enemy attack
    void doNothing(ActionEvent event) {
    	Random r = new Random();
		int rand = r.nextInt((2 - 1) + 1) + 1;
		playerMove.setText("");
		
		if (player.getHp() > 0 && enemy.getHp() > 0) {
		
		if (rand == 1) {
		int damageEnemy = player.getHp() - (enemy.getEnemyDamage()+enemyDamage);
		int enemyDamageDone = player.getHp() - damageEnemy;
		player.setHp(damageEnemy);
		playerHealthLabel.setText("Player Health: " + player.getHp());
		enemyMove.setText("Enemy did " + enemyDamageDone + " damage");
	
			if (player.getHp() <=0) {
				enemyMove.setText("Enemy won!");
				playerMove.setText("Enemy did " + enemyDamageDone + " damage");
				}
			}
		else if (rand == 2 && enemy.getEp() >= 5){
			int energyDamage = player.getHp() - (enemy.getEnergyDamage()+enemyDamage);
			int energyDamageDone = player.getHp() - energyDamage;
			int enemyEnergyUsed = enemy.getEp() - 5;
			enemy.setEp(enemyEnergyUsed);
			enemyEnergyLabel.setText("Enemy Energy: " + enemy.getEp());
			player.setHp(energyDamage);
			playerHealthLabel.setText("Player Health: " + player.getHp());
    		enemyMove.setText("Enemy did " + energyDamageDone + " energy damage");
    		
    		if (player.getHp() <=0) {
				enemyMove.setText("Enemy won!");
				playerMove.setText("Enemy did " + energyDamageDone + " energy damage");
				}
			}
		} else { playerMove.setText("Game is over, reset to start a new game");
		 enemyMove.setText("");
		}
    }
    
	@FXML
    void goShop(ActionEvent shoppingEvent) {
    	System.out.println("Button Clicked");
    	Scene mainScene = applicationStage.getScene();
    	
    	VBox mainScreenVbox = new VBox(10);
    	
    	VBox ItemNumber1VBox = new VBox(10);
    	Button selectButton1 = new Button("Select");
    	Label itemNumber1Label  = new Label("Item #1");
    	TextField item1TextField = new TextField();

    	VBox ItemNumber2VBox = new VBox(10);
    	Button selectButton2 = new Button("Select");
    	Label itemNumber2Label  = new Label("Item #2");
    	TextField item2TextField = new TextField();
    	
    	VBox ItemNumber3VBox = new VBox(10);
    	Button selectButton3 = new Button("Select");
    	Label itemNumber3Label  = new Label("Item #3");
    	TextField item3TextField = new TextField();
    	
    	Label overallPurchaseLabel  = new Label("Overall Purchase is: ");
    	
    	Button menuButton = new Button("Back to Menu");
    	
    	mainScreenVbox.getChildren().addAll(menuButton,ItemNumber1VBox, ItemNumber2VBox, ItemNumber3VBox);
    	
    	ItemNumber1VBox.getChildren().addAll(itemNumber1Label,item1TextField, selectButton1);
    	ItemNumber2VBox.getChildren().addAll(itemNumber2Label,item2TextField, selectButton2);
    	ItemNumber3VBox.getChildren().addAll(itemNumber3Label,item3TextField,selectButton3,overallPurchaseLabel);
    	
    	Scene ShoppingScene = new Scene(mainScreenVbox, 400, 400);
    	applicationStage.setScene(ShoppingScene);
    	menuButton.setOnAction(menuEvent -> applicationStage.setScene(mainScene));
    	
	}
    	//if (totalPrice =< coins) {
    		
    	
    	
    	//double totalPrice = 0.0;
	    //String quantityEntered = item1TextField.getText();
	 	
		
  
//                 item 1 
//    			   //health - 500
//    			   //if coin > 0 
//    			   //else "health points not enough
//    		       
//    		       System.out.println("You have clicked the button 1 " + count + " times");
//    		       //total price = count*itemPrice
//    		
//    			   item 2
//    			   //coin - 300
//    			   //if health > 0 
//    			   //else "health points not enough
//    			   
//    			   //total price = count*itemPrice
//    			   item 3
//    			   //coin - 200
//    			   //if health > 0 true
//    			   //else "health points not enough
//    			   
//    			  
//    		
//    	
	
	
	Label xpUpgrade = new Label("EXP: "+ experience.getXp());
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
    	
    	xpUpgrade.setText("EXP: "+ experience.getXp());
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
    	if (experience.getXp() >= 15) {
    	int xpLeft = experience.getXp() - 15;
    	experience.setXp(xpLeft);
    	xpUpgrade.setText("EXP: "+xpLeft);
    	xp = xpLeft;
    	
    	xpEarned.setText("EXP: "+experience.getXp());
		xpLabel.setText("EXP: "+experience.getXp());
    	
    	healthMultiplier +=1;
    	description.setText("Health has been increased by 5!");
    	}
    	else {
    		description.setText("Not enough EXP");
    	}
    }
    
    void energyIncrease(ActionEvent event) {
    	if (experience.getXp() >= 20) {
    	int xpLeft = experience.getXp() - 20;
    	experience.setXp(xpLeft);
    	xpUpgrade.setText("EXP: "+xpLeft);
    	xp = xpLeft;
    	
    	xpEarned.setText("EXP: "+experience.getXp());
		xpLabel.setText("EXP: "+experience.getXp());
    	
    	energyMultiplier +=1;
    	description.setText("Energy has been increased by 5!");
    	}
    	else {
    		description.setText("Not enough EXP");
    	}
    }
    
    void damageIncrease(ActionEvent event) {
    	if (experience.getXp() >= 30) {
    	int xpLeft = experience.getXp() - 30;
    	experience.setXp(xpLeft);
    	xpUpgrade.setText("EXP: "+xpLeft);
    	xp = xpLeft;
    	
    	xpEarned.setText("EXP: "+experience.getXp());
		xpLabel.setText("EXP: "+experience.getXp());
    	
    	damageMultiplier +=1;
    	description.setText("Damage has been increased by 1!");
    	}
    	else {
    		description.setText("Not enough EXP");
    	}
    }
}