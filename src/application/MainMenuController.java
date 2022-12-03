package application;

import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;

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
    
    
    Player player = new Player(10, 10);
	Enemy enemy = new Enemy("",10,10);
    
    private Button attackButton;
    private Button specialAttackButton;
	
    //int phealth = 100;
	//int penergy = 100;
	
	//Player player = new Player(phealth, penergy);
    
	//int ehealth = 100;
	//int eenergy = 100;
	//Enemy enemy = new Enemy(ename, ehealth, eenergy);
	
	//int damageDelt = 0;
	
	Label playerMove = new Label("");
	Label enemyMove = new Label("");
	
	Label playerHealthLabel = new Label("");
	Label playerEnergyLabel = new Label("");
	Label enemyHealthLabel = new Label("");
	Label enemyEnergyLabel = new Label("");
	
	
	int coins =0;
	Label coinsEarned = new Label("Coins: " + coins);
	
    @FXML
    void startGame(ActionEvent startGameEvent) {
    	Scene mainScene = applicationStage.getScene();
    	
    	String playerName = nameTextField.getText();
    	
    	playerMove.setText("");
    	enemyMove.setText("");
    	
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
    	player.setHp(10);
    	player.setEp(10);
    	
    	enemy.setHp(10);
    	enemy.setEp(10);
    	  
        String[] nameArray = {"Bob", "Jack", "Chris", "Brett", "Nika"};
    	Random randomName = new Random();
    	int index = randomName.nextInt(nameArray.length);
    	String ename = nameArray[index];
    	
    	enemy.setName(ename);
    	
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
    	moves.getChildren().addAll(playerMove, enemyMove, coinsEarned);
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
    	int damageTaken = enemy.getHp() - player.getDamage();
    	int damageDone = enemy.getHp() - damageTaken;
    	enemy.setHp(damageTaken);
    	enemyHealthLabel.setText("Enemy Health: " + enemy.getHp());
    	playerMove.setText("You did " + damageDone + " damage");
    	
    	// If enemy dies, win game and coins
    	if (enemy.getHp() <= 0) {
    		enemyMove.setText("You Won!");
    		playerMove.setText("You did " + damageDone + " damage");
    		Random r = new Random();
    		int rand = r.nextInt((5 - 1) + 1) + 1;
    		coins += rand;
    		coinsEarned.setText("Coins: " + coins);
    		coinLabel.setText("Coins: "+ coins);
    	}
    	else {
    		// enemies turn now, generates random value to decide enemies move
    		Random r = new Random();
    		int rand = r.nextInt((2 - 1) + 1) + 1;
    		
    		// regular attack, random damage between 1-5
    		if (rand == 1) {
    		int damageEnemy = player.getHp() - enemy.getEnemyDamage();
    		int enemyDamageDone = player.getHp() - damageEnemy;
    		player.setHp(damageEnemy);
    		playerHealthLabel.setText("Player Health: " + player.getHp());
    		enemyMove.setText("Enemy did " + enemyDamageDone + " damage");
    	
    			if (player.getHp() <=0) {
    				enemyMove.setText("Enemy won!");
    				playerMove.setText("Enemy did " + enemyDamageDone + " damage");
    				}
    			}
    		// energy attack, guaranteed 3 damage at the cost of 5 energy
    		else if (rand == 2 && enemy.getEp() >= 5){
    			int energyDamage = player.getHp() - enemy.getEnergyDamage();
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
    			
    			int damageDone = enemy.getHp() - player.getEnergyDamage();
    			enemy.setHp(damageDone);
    			int energyUsed = player.getEp() - 5;
    			player.setEp(energyUsed);
    			enemyHealthLabel.setText("Enemy Health: " + enemy.getHp());
    			playerEnergyLabel.setText("Player Energy: " + player.getEp());
    	    	playerMove.setText("You did " + player.getEnergyDamage() + " energy damage");
    	    	
    	    	if (enemy.getHp() <= 0) {
    	    		enemyMove.setText("You Won!");
    	    		playerMove.setText("You did "+ player.getEnergyDamage() + " energy damage");
    	    		Random r = new Random();
    	    		int rand = r.nextInt((5 - 1) + 1) + 1;
    	    		coins += rand;
    	    		coinsEarned.setText("Coins: " + coins);
    	    		coinLabel.setText("Coins: "+ coins);
    	    	}
    	    	else {
    	    		Random r = new Random();
    	    		int rand = r.nextInt((2 - 1) + 1) + 1;
    	    		
    	    		if (rand == 1) {
    	    		int damageEnemy = player.getHp() - enemy.getEnemyDamage();
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
    	    			int energyDamage = player.getHp() - enemy.getEnergyDamage();
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
		int damageEnemy = player.getHp() - enemy.getEnemyDamage();
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
			int energyDamage = player.getHp() - enemy.getEnergyDamage();
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
    void goShop(ActionEvent event) {
    	System.out.println("Button Clicked");
    	Scene mainScene = applicationStage.getScene();
    	
    	VBox mainScreenVbox = new VBox(10);
    	
    	VBox ItemNumber1VBox = new VBox(10);
    	Button selectButton1 = new Button("Select");
    	Label itemNumber1Label  = new Label("Item #1");


    	VBox ItemNumber2VBox = new VBox(10);
    	Button selectButton2 = new Button("Select");
    	Label itemNumber2Label  = new Label("Item #2");
    	
    	VBox ItemNumber3VBox = new VBox(10);
    	Button selectButton3 = new Button("Select");
    	Label itemNumber3Label  = new Label("Item #3");
    	
    	Label overallPurchaseLabel  = new Label("Overall Purchase is: ");
    	
    	Button menuButton = new Button("Back to Menu");
    	
    	mainScreenVbox.getChildren().addAll(menuButton,ItemNumber1VBox, ItemNumber2VBox, ItemNumber3VBox);
    	
    	ItemNumber1VBox.getChildren().addAll(itemNumber1Label, selectButton1);
    	ItemNumber2VBox.getChildren().addAll(itemNumber2Label, selectButton2);
    	ItemNumber3VBox.getChildren().addAll(itemNumber3Label,selectButton3,overallPurchaseLabel);
    	
    	Scene ShoppingScene = new Scene(mainScreenVbox, 400, 400);
    	applicationStage.setScene(ShoppingScene);
    
    
    	menuButton.setOnAction(menuEvent -> applicationStage.setScene(mainScene));
    }

    @FXML
    void goUpgrades(ActionEvent event) {
    	System.out.println("Button Clicked");
    }
}