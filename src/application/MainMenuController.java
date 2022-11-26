package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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
    	HBox stats = new HBox(100);
    	// contains specific stats for enemy
    	VBox enemyStats = new VBox(10);
    	//contains specific stats for player
    	VBox playerStats = new VBox(10);
    	// next level after stats, contains the first set of options for player
    	HBox options1 = new HBox(100);
    	Scene startGameScene = new Scene(startGameContainer, 400, 400);
    	
    	Label enemyNameLabel = new Label("Enemy Name:");
    	Label enemyHealthLabel = new Label("Enemy Health:");
    	Label playerNameLabel = new Label("Player Name: " + playerName);
    	Label playerHealthLabel = new Label("Player Health:");
    	applicationStage.setTitle("Started Game");
    	    	
    	Button menuButton = new Button("Back to Menu");
    	menuButton.setOnAction(menuEvent -> applicationStage.setScene(mainScene));
    	
    	Button attackButton = new Button("Attack");
    	attackButton.setLayoutX(100);
    	attackButton.setLayoutY(80);
    	
    	Button itemButton = new Button("Items");
    	itemButton.setLayoutX(100);
    	itemButton.setLayoutY(80);
    	
    	VBox.setMargin(attackButton, new Insets(0,0,0,25));
    	VBox.setMargin(itemButton, new Insets(0,0,0,25));
    	
    	VBox.setMargin(menuButton, new Insets(0,0,0,0));
    	VBox.setMargin(enemyNameLabel, new Insets(0,0,0,25));
    	VBox.setMargin(enemyHealthLabel, new Insets(0,0,0,25));
    	VBox.setMargin(playerNameLabel, new Insets(0,0,0,25));
    	VBox.setMargin(playerHealthLabel, new Insets(0,0,0,25));
    	
    	
    	startGameContainer.getChildren().addAll(menuButton, stats, options1);
    	options1.getChildren().addAll(attackButton, itemButton);
    	enemyStats.getChildren().addAll(enemyNameLabel, enemyHealthLabel);
    	playerStats.getChildren().addAll(playerNameLabel, playerHealthLabel);
    	stats.getChildren().addAll(enemyStats, playerStats);
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
