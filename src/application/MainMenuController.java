package application;


import java.util.ArrayList;

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
    private TextField item1TextField;
    
    @FXML
    private TextField item2TextField;
    
    @FXML
    private TextField item3TextField;
    
    
    
    
    
    Player player = new Player(10, 10);
	Enemy enemy = new Enemy("test",10,10);
    
    private Button attackButton;
    private Button specialAttackButton;
	
    //int phealth = 100;
	//int penergy = 100;
	
	//Player player = new Player(phealth, penergy);
	
	//String[] nameArray = {"Bob", "Jack", "Chris"};
	//Random randomName = new Random();
	//int index = randomName.nextInt(nameArray.length);
	//String ename = nameArray[index];
	//int ehealth = 100;
	//int eenergy = 100;
	//Enemy enemy = new Enemy(ename, ehealth, eenergy);
	
	//int damageDelt = 0;
	
	Label playerMove = new Label("");
	Label enemyMove = new Label("");
	
	Label playerHealthLabel = new Label("");
	Label enemyHealthLabel = new Label("");
	
    @FXML
    void startGame(ActionEvent startGameEvent) {
    	Scene mainScene = applicationStage.getScene();
    	
    	String playerName = nameTextField.getText();
    	
    	
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
    	
    	// ALL PLAYER/ENEMY STATS DISPLAY
    	Label enemyNameLabel = new Label("Enemy Name: " + enemy.getName());
    	enemyHealthLabel.setText("Enemy Health: " + enemy.getHp());
     	Label enemyEnergyLabel = new Label("Enemy Energy: " + enemy.getEp());
    	Label playerNameLabel = new Label("Player Name: " + playerName);
    	playerHealthLabel.setText("Player Health: " + player.getHp());
    	Label playerEnergyLabel = new Label("Player Energy: " + player.getEp());
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
    	Button endTurn = new Button("Do Nothing");
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
    	moves.getChildren().addAll(playerMove, enemyMove);
    	itemBox.getChildren().addAll(itemsLabel, itemsChoiceBox, itemsButton);
    	options1.getChildren().addAll( attackButton, itemBox);
    	turnEnd.getChildren().addAll(endTurn);
    	options2.getChildren().addAll(specialAttackButton, turnEnd);
    	enemyStats.getChildren().addAll(enemyNameLabel, enemyHealthLabel, enemyEnergyLabel);
    	playerStats.getChildren().addAll(playerNameLabel, playerHealthLabel, playerEnergyLabel);
    	stats.getChildren().addAll(playerStats, enemyStats);
    	applicationStage.setScene(startGameScene);

    }

    void attackEvent(ActionEvent attackEvent) {
    	
    	if (player.getHp() > 0 && enemy.getHp() > 0) {
    	
    	int damageTaken = enemy.getHp() - player.damage;
    	enemy.setHp(damageTaken);
    	enemyHealthLabel.setText("Enemy Health: " + enemy.getHp());
    	playerMove.setText("You did " + player.damage + " damage");
    	
    	if (enemy.getHp() <= 0) {
    		playerMove.setText("You Won!");
    		enemyMove.setText("");
    	}
    	else {
    	int damageEnemy = player.getHp() - enemy.enemyDamage;
    	player.setHp(damageEnemy);
    	playerHealthLabel.setText("Player Health: " + player.getHp());
    	enemyMove.setText("Enemy did " + enemy.enemyDamage + " damage");
    	
    	if (player.getHp() <=0) {
    		enemyMove.setText("Enemy won!");
    		playerMove.setText("");
    			}
    		}
    	}
    	else { playerMove.setText("Game is over, reset to start a new game");
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
    
    	
    private void Action(ActionEvent event) {
    	
    	double totalPrice = 0.0;
	String quantityEntered = item1TextField.getText();
	 	
		
	}
  
//                 item 1 
//    			   //health - 500
//    			   //if health > 0 true
//    			   //else "health points not enough
//    		       
//    		       System.out.println("You have clicked the button 1 " + count + " times");
//    		       //total price = count*itemPrice
//    		
//    			   item 2
//    			   //health - 300
//    			   //if health > 0 true
//    			   //else "health points not enough
//    			   System.out.println("You have clicked the button 2 " + count + " times");
//    			   //total price = count*itemPrice
//    			   item 3
//    			   //health - 200
//    			   //if health > 0 true
//    			   //else "health points not enough
//    			   
//    			  
//    		
//    		  
    @FXML
    void goUpgrades(ActionEvent event) {
    	System.out.println("Button Clicked");
    }
}