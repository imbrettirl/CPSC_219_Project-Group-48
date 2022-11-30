package application;

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
    void startGame(ActionEvent startGameEvent) {
    	Scene mainScene = applicationStage.getScene();
    	
    	String playerName = nameTextField.getText();

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
    	HBox options2 = new HBox(10);
    	options2.setPadding(new Insets(0,0,0,25));
    	HBox turnEnd = new HBox(0);
    	turnEnd.setPadding(new Insets(0,0,0,25));
    	VBox moves = new VBox(20);
    	moves.setPadding(new Insets(50,0,0,25));
    	Scene startGameScene = new Scene(startGameContainer, 400, 400);

    	
    	
    	int phealth = 100;
    	int penergy = 100;
    	Player player = new Player(phealth, penergy);
    	
    	String[] nameArray = {"Bob", "Jack", "Chris"};
    	Random randomName = new Random();
    	int index = randomName.nextInt(nameArray.length);
    	String ename = nameArray[index];
    	int ehealth = 100;
    	int eenergy = 100;
    	Enemy enemy = new Enemy(ename, ehealth, eenergy);
    	
    	
    	Label enemyNameLabel = new Label("Enemy Name: " + enemy.getName());
    	Label enemyHealthLabel = new Label("Enemy Health: " + enemy.getHp());
     	Label enemyEnergyLabel = new Label("Enemy Energy: " + enemy.getEp());
    	Label playerNameLabel = new Label("Player Name: " + playerName);
    	Label playerHealthLabel = new Label("Player Health: " + player.getHp());
    	Label playerEnergyLabel = new Label("Player Energy: " + player.getEp());
    	applicationStage.setTitle("Started Game");
    	    	
    	Button menuButton = new Button("Back to Menu");
    	menuButton.setOnAction(menuEvent -> applicationStage.setScene(mainScene));
    	
    	//options1
    	Label attackLabel = new Label("Attack");
    	ChoiceBox<String> attackChoiceBox = new ChoiceBox<String>();
    	attackChoiceBox.getItems().add("Basic Attack");
    	
    	Label itemsLabel = new Label("Items");
    	ChoiceBox<String> itemsChoiceBox = new ChoiceBox<String>();
    	
    	//options2
    	Label specials = new Label("Specials");
    	ChoiceBox<String> specialChoiceBox = new ChoiceBox<String>();
    	specialChoiceBox.getItems().add("Basic Spell");
    	
    	Button endTurn = new Button("End Turn");
    	endTurn.setLayoutX(80);
    	
    	
    	Label playerMove = new Label("placeholder");
    	Label enemyMove = new Label("placeholder");
    	playerMove.setLayoutX(20);
    	enemyMove.setLayoutX(20);
    	
    	
    	VBox.setMargin(enemyNameLabel, new Insets(0,0,0,25));
    	VBox.setMargin(enemyHealthLabel, new Insets(0,0,0,25));
    	VBox.setMargin(enemyEnergyLabel, new Insets(0,0,0,25));

    	VBox.setMargin(playerNameLabel, new Insets(0,0,0,25));
    	VBox.setMargin(playerHealthLabel, new Insets(0,0,0,25));
    	VBox.setMargin(playerEnergyLabel, new Insets(0,0,0,25));
    	
    	startGameContainer.getChildren().addAll(menuButton, stats, options1, options2, moves);
    	moves.getChildren().addAll(playerMove, enemyMove);
    	options1.getChildren().addAll(attackLabel, attackChoiceBox, itemsLabel, itemsChoiceBox);
    	turnEnd.getChildren().addAll(endTurn);
    	options2.getChildren().addAll(specials, specialChoiceBox, turnEnd);
    	enemyStats.getChildren().addAll(enemyNameLabel, enemyHealthLabel, enemyEnergyLabel);
    	playerStats.getChildren().addAll(playerNameLabel, playerHealthLabel, playerEnergyLabel);
    	stats.getChildren().addAll(playerStats, enemyStats);
    	applicationStage.setScene(startGameScene);
    }

    @FXML
    void goShop(ActionEvent event) {
    	System.out.println("Button Clicked");
    }

    @FXML
    void goUpgrades(ActionEvent event) {
    	System.out.println("Button Clicked");
    }

}
