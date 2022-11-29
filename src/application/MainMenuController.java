package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
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