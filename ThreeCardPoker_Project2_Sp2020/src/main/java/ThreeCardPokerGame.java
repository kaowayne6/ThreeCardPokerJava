
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ThreeCardPokerGame extends Application {

	//Variables for game
	private Player playerOne;
	private Player playerTwo;
	private Dealer theDealer;
	private int option;	//This is to track current setting of new look
	private int turn = 1;	//1. Player1 bet
							//2. Player2 bet
							//3. Player1 decision to fold or play
							//4. Player2 decision to fold or play
							//5. Dealer shows card. Win or lose amount shown(Play again or quit)
	
	//Variables for scene layout
	final String paneOption1 = "-fx-background-image: url(StartBackground.jpg);";
	final String paneOption2 = "-fx-background-image: url(Background2.jpg);";
	final String menuBarOption1 = "-fx-background-color: linear-gradient(to top, #06F94F, #03B7FF);";
	final String menuBarOption2 = "-fx-background-color: linear-gradient(to top, #9C00BE, #1A0FF0);";
	final String buttonOption1 = "-fx-background-color: linear-gradient(#62CFF7, #AFE3F6);";
	final String buttonOption2 = "-fx-background-color: linear-gradient(to top, #FE3D10, #F0970E);";
	final String buttonSize = "-fx-font-size: 25;";
	final String fontSize = "-fx-font-size: 50;";
	
	//Size of intitial cards in start screen
	final int height = 200;
	final int width = 200;
	//Button width
	final int buttonWidth = 180;
	
	//General scene layout variables
	private Stage stage;
	private BorderPane paneCenter;
	private MenuBar menubar;
	private Menu menu;
	private MenuItem m1;
	private MenuItem m2;
	private MenuItem m3;
	
	//Start game variables
	ListView<String> p1Stat;
	ObservableList<String> queueItems;
	//Turn 1 and 2
	Label msg;
	Label msg2;
	Image card_back;
	ImageView card_backV;
	Label p1;
	Label anteLabel;
	Label ppLabel;
	TextField anteBet;
	TextField ppBet;
	Label p2;
	Button submit;
	private HBox topBox;
	private HBox anteBox;
	private HBox ppBox;
	private VBox txtBox;
	private HBox bottomInfo;
	
	//Turn 3 and 4
	//Card Images and Imageview
	private HBox p1Box;
	private HBox p2Box;
	private HBox playerCardBox;
	private HBox dealerCardBox;
	private VBox allCardsBox;
	private VBox playFoldBox;
	private HBox bottomPlayFoldBox;
	private TextField playCards;
	private Label playCardsLabel;
	private Label playFoldMsg;
	private VBox msgAndTxt;
	Image p1_1Card;
	ImageView p1_1V;
	Image p1_2Card;
	ImageView p1_2V;
	Image p1_3Card;
	ImageView p1_3V;
	Image p2_1Card;
	ImageView p2_1V;
	Image p2_2Card;
	ImageView p2_2V;
	Image p2_3Card;
	ImageView p2_3V;
	Button fold;
	Button play;
	
	//Turn 4 and 5
	Image d1Card;
	ImageView d1V;
	Image d2Card;
	ImageView d2V;
	Image d3Card;
	ImageView d3V;
	private Label p1Wins;
	private Label p2Wins;
	private VBox endGameWins;
	private VBox endGameOptions;
	private HBox endGameBottom;
	Button playAgain;
	Button quitGame;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	//This is the start screen scene
	public Scene StartScene() {
		
		BorderPane paneCenter = new BorderPane();
		paneCenter.setStyle(paneOption1);
		
		//menu related items
		menubar = new MenuBar();
		menu = new Menu("Options");
		m1 = new MenuItem("Exit");
		m2 = new MenuItem("Fresh Start");
		m3 = new MenuItem("New Look");
		menu.getItems().add(m1);
		menu.getItems().add(m2);
		menu.getItems().add(m3);
		menubar.getMenus().add(menu);
		menu.setStyle(menuBarOption1);
		menubar.setStyle(menuBarOption1);
		
		
		Button playBtn = new Button("Play");
		Button instructBtn = new Button("Instructions");
		Button quitBtn = new Button("Quit");
		
		Label l1 = new Label("Three Card");
		Label l2 = new Label("Poker");
		Image pic = new Image("14S.png");
		ImageView v = new ImageView(pic);
		Image pic2 = new Image("13S.png");
		ImageView v2 = new ImageView(pic2);
		Image pic3 = new Image("12S.png");
		ImageView v3 = new ImageView(pic3);
		
		HBox flushImage = new HBox(v,v2,v3);	//puts the three cards together
		//This makes the top of the BorderPane putting menuBar at top and flush image below
		VBox topPane = new VBox(100,menubar, flushImage);
		HBox btnBox = new HBox(playBtn, instructBtn, quitBtn);
		VBox labelBox = new VBox(l1,l2, btnBox);
		
		//The Menubar and it's options
		m1.setOnAction(e -> System.exit(0) );
		m2.setOnAction(e -> {});
		m3.setOnAction(e -> {
			if(option == 0) {
				option = 1;
				l1.setStyle(fontSize + "-fx-text-fill: blue;");
				menu.setStyle(menuBarOption2);
				menubar.setStyle(menuBarOption2);
				paneCenter.setStyle(paneOption2);
				playBtn.setStyle(buttonSize + buttonOption2);
				instructBtn.setStyle(buttonSize + buttonOption2);
				quitBtn.setStyle(buttonSize + buttonOption2);
			}
			
			else {
				option = 0;
				l1.setStyle(fontSize + "-fx-text-fill: #9BC2CF;");
				menu.setStyle(menuBarOption1);
				menubar.setStyle(menuBarOption1);
				paneCenter.setStyle(paneOption1);
				playBtn.setStyle(buttonSize + buttonOption1);
				instructBtn.setStyle(buttonSize + buttonOption1);
				quitBtn.setStyle(buttonSize + buttonOption1);
			}
			
		});
		//Sets ace spades picture
		v.setFitHeight(height);
		v.setFitWidth(width);
		v.setPreserveRatio(true);
		
		//Sets King of spades picture
		v2.setFitHeight(height);
		v2.setFitWidth(width);
		v2.setPreserveRatio(true);
		
		//Sets King of spades picture
		v3.setFitHeight(height);
		v3.setFitWidth(width);
		v3.setPreserveRatio(true);
		
		//Puts the Ace King and Queen on top next to each other
		flushImage.setSpacing(100);
		flushImage.setAlignment(Pos.TOP_CENTER);
	

		//Buttons to be placed at the bottom
		playBtn.setPrefSize(buttonWidth, 50);
		playBtn.setStyle(buttonSize + buttonOption1);
		playBtn.setOnAction(e -> { stage.setScene(playGameScene());});
		instructBtn.setPrefSize(buttonWidth, 50);
		instructBtn.setStyle(buttonSize + buttonOption1);
		quitBtn.setPrefSize(buttonWidth, 50);
		quitBtn.setStyle(buttonSize + buttonOption1);
		quitBtn.setOnAction(e -> System.exit(0));
		btnBox.setAlignment(Pos.CENTER);
		btnBox.setSpacing(70);
		btnBox.setPadding(new Insets(70,0,0,0));
		
		//Sets the middle label
		l1.setStyle(fontSize + "-fx-text-fill: #9BC2CF;");
		l2.setStyle(fontSize + "-fx-text-fill: red;");
		labelBox.setPadding(new Insets(50,0,0,0));
		labelBox.setAlignment(Pos.TOP_CENTER);
		
		//Sets up the actual scene and return the scene
		paneCenter.setCenter(labelBox);
		paneCenter.setTop(topPane);
		
		return new Scene(paneCenter, 1200, 750);
		
	}
	
	//This fills an arrayList with player 1 and 2 data for observable list
	private ArrayList<String> fillArrayListData() {
		ArrayList<String> dataArr = new ArrayList<String>();
		dataArr.add("--Player 1--");
		dataArr.add("Ante Bet: $" + playerOne.getAnteBet());
		dataArr.add("Pair Plus Bet: $" + playerOne.getPairPlusBet());
		dataArr.add("Play Bet: $" + playerOne.getPlayBet());
		dataArr.add("Total Winnings: " + playerOne.getTotalWinnings());
		dataArr.add("Money: $" + playerOne.getTotalMoney());
		
		dataArr.add("--Player 2--");
		dataArr.add("Ante Bet: $" + playerTwo.getAnteBet());
		dataArr.add("Pair Plus Bet: $" + playerTwo.getPairPlusBet());
		dataArr.add("Play Bet: $" + playerTwo.getPlayBet());
		dataArr.add("Total Winnings: " + playerTwo.getTotalWinnings());
		dataArr.add("Money: $" + playerTwo.getTotalMoney());
		
		return dataArr;
	}
	
	//Return the String of the card type to retrieve image
	private String getCardString(Card c) {
		
		String s = "" + c.getValue();
		s += Character.toString(c.getSuit());
		return s;
	}
	
	//This function sets the card images up so that it only shows Player 1 Cards
	private void showP1Cards() {
		//Setup images and put them in the right boxes
		String c1 = getCardString(playerOne.getHand().get(0));	//player 1
		String c2 = getCardString(playerOne.getHand().get(1));
		String c3 = getCardString(playerOne.getHand().get(2));
		p1_1Card = new Image(c1 + ".png");
		p1_2Card = new Image(c2 + ".png");
		p1_3Card = new Image(c3 + ".png");
		p1_1V = new ImageView(p1_1Card);
		p1_2V = new ImageView(p1_2Card);
		p1_3V = new ImageView(p1_3Card);
		p1Box = new HBox(p1_1V, p1_2V, p1_3V);
		
		
		int smallCardSize = 150;
		//Scales the images correctly
		p1_1V.setFitHeight(smallCardSize);
		p1_1V.setFitWidth(smallCardSize);
		p1_1V.setPreserveRatio(true);
		p1_2V.setFitHeight(smallCardSize);
		p1_2V.setFitWidth(smallCardSize);
		p1_2V.setPreserveRatio(true);
		p1_3V.setFitHeight(smallCardSize);
		p1_3V.setFitWidth(smallCardSize);
		p1_3V.setPreserveRatio(true);
		
		p2_1Card = new Image("CardBack.png");	//Player 2
		p2_2Card = new Image("CardBack.png");
		p2_3Card = new Image("CardBack.png");
		p2_1V = new ImageView(p2_1Card);
		p2_2V = new ImageView(p2_2Card);
		p2_3V = new ImageView(p2_3Card);
		p2Box = new HBox(p2_1V, p2_2V, p2_3V);
		
		p2_1V.setFitHeight(smallCardSize);
		p2_1V.setFitWidth(smallCardSize);
		p2_1V.setPreserveRatio(true);
		p2_2V.setFitHeight(smallCardSize);
		p2_2V.setFitWidth(smallCardSize);
		p2_2V.setPreserveRatio(true);
		p2_3V.setFitHeight(smallCardSize);
		p2_3V.setFitWidth(smallCardSize);
		p2_3V.setPreserveRatio(true);
		
		d1Card = new Image("CardBack.png");
		d2Card = new Image("CardBack.png");
		d3Card = new Image("CardBack.png");
		d1V = new ImageView(d1Card);
		d2V = new ImageView(d2Card);
		d3V = new ImageView(d3Card);
		dealerCardBox = new HBox(d1V, d2V, d3V);
		dealerCardBox.setPadding(new Insets(0,0,0,300));
		
		d1V.setFitHeight(smallCardSize);
		d1V.setFitWidth(smallCardSize);
		d1V.setPreserveRatio(true);
		d2V.setFitHeight(smallCardSize);
		d2V.setFitWidth(smallCardSize);
		d2V.setPreserveRatio(true);
		d3V.setFitHeight(smallCardSize);
		d3V.setFitWidth(smallCardSize);
		d3V.setPreserveRatio(true);
	}
	
	//This function is to only show Player 2 cards
	private void showP2Cards() {
		//Puts player1 cards back face down
		p1_1Card = new Image("CardBack.png");
		p1_2Card = new Image("CardBack.png");
		p1_3Card = new Image("CardBack.png");
		p1_1V = new ImageView(p1_1Card);
		p1_2V = new ImageView(p1_2Card);
		p1_3V = new ImageView(p1_3Card);
		p1Box = new HBox(p1_1V, p1_2V, p1_3V);
		
		int smallCardSize = 150;
		//Scales the images correctly
		p1_1V.setFitHeight(smallCardSize);
		p1_1V.setFitWidth(smallCardSize);
		p1_1V.setPreserveRatio(true);
		p1_2V.setFitHeight(smallCardSize);
		p1_2V.setFitWidth(smallCardSize);
		p1_2V.setPreserveRatio(true);
		p1_3V.setFitHeight(smallCardSize);
		p1_3V.setFitWidth(smallCardSize);
		p1_3V.setPreserveRatio(true);
		
		//Setup images and put them in the right boxes
		String c1 = getCardString(playerTwo.getHand().get(0));	//player 1
		String c2 = getCardString(playerTwo.getHand().get(1));
		String c3 = getCardString(playerTwo.getHand().get(2));
		p2_1Card = new Image(c1 + ".png");
		p2_2Card = new Image(c2 + ".png");
		p2_3Card = new Image(c3 + ".png");
		p2_1V = new ImageView(p2_1Card);
		p2_2V = new ImageView(p2_2Card);
		p2_3V = new ImageView(p2_3Card);
		p2Box = new HBox(p2_1V, p2_2V, p2_3V);
		
		//Resizes player 2 cards
		p2_1V.setFitHeight(smallCardSize);
		p2_1V.setFitWidth(smallCardSize);
		p2_1V.setPreserveRatio(true);
		p2_2V.setFitHeight(smallCardSize);
		p2_2V.setFitWidth(smallCardSize);
		p2_2V.setPreserveRatio(true);
		p2_3V.setFitHeight(smallCardSize);
		p2_3V.setFitWidth(smallCardSize);
		p2_3V.setPreserveRatio(true);
		
		d1Card = new Image("CardBack.png");
		d2Card = new Image("CardBack.png");
		d3Card = new Image("CardBack.png");
		d1V = new ImageView(d1Card);
		d2V = new ImageView(d2Card);
		d3V = new ImageView(d3Card);
		dealerCardBox = new HBox(d1V, d2V, d3V);
		dealerCardBox.setPadding(new Insets(0,0,0,300));
		
		d1V.setFitHeight(smallCardSize);
		d1V.setFitWidth(smallCardSize);
		d1V.setPreserveRatio(true);
		d2V.setFitHeight(smallCardSize);
		d2V.setFitWidth(smallCardSize);
		d2V.setPreserveRatio(true);
		d3V.setFitHeight(smallCardSize);
		d3V.setFitWidth(smallCardSize);
		d3V.setPreserveRatio(true);
	}
	
	//This function is to show all Cards
	private void showAllCards() {
		
		//Gets string of card file name
		String c1 = getCardString(playerOne.getHand().get(0));	//player 1
		String c2 = getCardString(playerOne.getHand().get(1));
		String c3 = getCardString(playerOne.getHand().get(2));
		
		//Puts player1 cards back face down
		p1_1Card = new Image(c1+".png");
		p1_2Card = new Image(c2+".png");
		p1_3Card = new Image(c3+".png");
		p1_1V = new ImageView(p1_1Card);
		p1_2V = new ImageView(p1_2Card);
		p1_3V = new ImageView(p1_3Card);
		p1Box = new HBox(p1_1V, p1_2V, p1_3V);
		
		int smallCardSize = 150;
		//Scales the images correctly
		p1_1V.setFitHeight(smallCardSize);
		p1_1V.setFitWidth(smallCardSize);
		p1_1V.setPreserveRatio(true);
		p1_2V.setFitHeight(smallCardSize);
		p1_2V.setFitWidth(smallCardSize);
		p1_2V.setPreserveRatio(true);
		p1_3V.setFitHeight(smallCardSize);
		p1_3V.setFitWidth(smallCardSize);
		p1_3V.setPreserveRatio(true);
		
		c1 = getCardString(playerTwo.getHand().get(0));	//player 2
		c2 = getCardString(playerTwo.getHand().get(1));
		c3 = getCardString(playerTwo.getHand().get(2));
		p2_1Card = new Image(c1 + ".png");
		p2_2Card = new Image(c2 + ".png");
		p2_3Card = new Image(c3 + ".png");
		p2_1V = new ImageView(p2_1Card);
		p2_2V = new ImageView(p2_2Card);
		p2_3V = new ImageView(p2_3Card);
		p2Box = new HBox(p2_1V, p2_2V, p2_3V);
		
		//Resizes player 2 cards
		p2_1V.setFitHeight(smallCardSize);
		p2_1V.setFitWidth(smallCardSize);
		p2_1V.setPreserveRatio(true);
		p2_2V.setFitHeight(smallCardSize);
		p2_2V.setFitWidth(smallCardSize);
		p2_2V.setPreserveRatio(true);
		p2_3V.setFitHeight(smallCardSize);
		p2_3V.setFitWidth(smallCardSize);
		p2_3V.setPreserveRatio(true);
		
		c1 = getCardString(theDealer.getDealersHand().get(0));	//dealer
		c2 = getCardString(theDealer.getDealersHand().get(1));
		c3 = getCardString(theDealer.getDealersHand().get(2));
		d1Card = new Image(c1+".png");
		d2Card = new Image(c2+".png");
		d3Card = new Image(c3+".png");
		d1V = new ImageView(d1Card);
		d2V = new ImageView(d2Card);
		d3V = new ImageView(d3Card);
		dealerCardBox = new HBox(d1V, d2V, d3V);
		dealerCardBox.setPadding(new Insets(0,0,0,300));
		
		d1V.setFitHeight(smallCardSize);
		d1V.setFitWidth(smallCardSize);
		d1V.setPreserveRatio(true);
		d2V.setFitHeight(smallCardSize);
		d2V.setFitWidth(smallCardSize);
		d2V.setPreserveRatio(true);
		d3V.setFitHeight(smallCardSize);
		d3V.setFitWidth(smallCardSize);
		d3V.setPreserveRatio(true);
	}
	
	private void setupEndGame() {
		//Calculates the pair plus winnings
		if(playerOne.getPairPlusBet() != 0 && !playerOne.getDidFold()) {
			int ppBetWin = (ThreeCardLogic.evalPPWinnings(playerOne.getHand(), playerOne.getPairPlusBet()));
			if(ppBetWin == 0)
				playerOne.setPairPlusBet(playerOne.getPairPlusBet() * -1);
			else
				playerOne.setPairPlusBet(ppBetWin);
		}
		if(playerTwo.getPairPlusBet() != 0 && !playerTwo.getDidFold()) {
			int ppBetWin = (ThreeCardLogic.evalPPWinnings(playerTwo.getHand(), playerTwo.getPairPlusBet()));
			if(ppBetWin == 0)
				playerTwo.setPairPlusBet(playerOne.getPairPlusBet() * -1);
			else
				playerOne.setPairPlusBet(ppBetWin);
		}
		
		//Tracks total winning both positive or negative
		int p1Winnings = 0;
		int p2Winnings = 0;
		
		//Calculates total winnings based on what happened in game
		if(playerOne.getDidFold()) {	//player 1
			p1Winnings = (playerOne.getAnteBet() + playerOne.getPairPlusBet() + 
					playerOne.getPlayBet()) * -1;
		}
		
		//Player wins
		else if(ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerOne.getHand()) == 2) {
			p1Winnings = ((playerOne.getAnteBet() + playerOne.getPlayBet()) * 2) + 
					playerOne.getPairPlusBet(); 
		}
		
		//Dealer wins
		else if(ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerOne.getHand()) == 1){
			 p1Winnings = playerOne.getPairPlusBet() + ((playerOne.getAnteBet() + playerOne.getPlayBet()) * -1);
			 playerOne.setTotalWinnings(playerOne.getTotalWinnings()+1);
		}
		
		//Tie
		else {
			p1Winnings = playerOne.getPairPlusBet();
		}
		
		if(playerTwo.getDidFold()) {	//player 2
			p2Winnings = (playerTwo.getAnteBet() + playerTwo.getPairPlusBet() + 
					playerTwo.getPlayBet()) * -1;
		}
		
		//Player wins
		else if(ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerTwo.getHand()) == 2) {
			p2Winnings = ((playerTwo.getAnteBet() + playerTwo.getPlayBet() * 2) + 
													playerTwo.getPairPlusBet()); 
			playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() + 1);
		}
		
		//Dealer wins
		else if(ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerTwo.getHand()) == 1){
			 p2Winnings = playerTwo.getPairPlusBet() + ((playerTwo.getAnteBet() + 
					 									playerTwo.getPlayBet()) * -1);
		}
		
		//tie
		else {
			p2Winnings = playerTwo.getPairPlusBet();
		}
		
		playerOne.setTotalMoney(playerOne.getTotalMoney() + p1Winnings);
		playerTwo.setTotalMoney(playerTwo.getTotalMoney() + p2Winnings);
		
		//Sets up screen to show winnings and play or quit options of the game
		p1Wins = new Label("Player 1 wins after round: " + p1Winnings + "$");
		p2Wins = new Label("Player 2 wins after round: " + p2Winnings + "$");
		p1Wins.setStyle("-fx-font-size: 20;" + "-fx-text-fill: red;");
		p2Wins.setStyle("-fx-font-size: 20;" + "-fx-text-fill: red;");
		endGameWins = new VBox(50, p1Wins, p2Wins);
		endGameOptions = new VBox(20, playAgain, quitGame);
		endGameBottom = new HBox(20, endGameWins, endGameOptions);
		
	}
	
	public Scene playGameScene() {
		//Setup for turn 1 and 2
		msg = new Label("Place a bet between $5 to $25 for ante and pair plus(optional)");
		msg.setStyle("-fx-text-fill: red;" + "-fx-font-size: 24");
		msg.setAlignment(Pos.TOP_CENTER);
		msg.setPadding(new Insets(50));
		p1 = new Label("Player 1");
		anteLabel = new Label("Ante Bet");
		ppLabel = new Label("Pair Plus Bet");
		anteBet = new TextField("0");
		ppBet = new TextField("0");
		p2 = new Label("Player 2");
		submit = new Button("Submit");
		playCardsLabel = new Label("Place Play Bets");
		
		//Turn 3 and 4
		//Card Images and Imageview
		fold = new Button("Fold");
		play = new Button("Play");
		
		//Turn 5
		playAgain = new Button("Play Again");
		quitGame = new Button("Quit");
		
		//Scene setup
		paneCenter = new BorderPane();
		
		//menu related items
		menubar = new MenuBar();
		menu = new Menu("Options");
		m1 = new MenuItem("Exit");
		m2 = new MenuItem("Fresh Start");
		m3 = new MenuItem("New Look");
		menu.getItems().add(m1);
		menu.getItems().add(m2);
		menu.getItems().add(m3);
		menubar.getMenus().add(menu);
		VBox mBar = new VBox(menubar);

		//Setting up looks of paneCenter and menubar
		if(option == 0) {
			menu.setStyle(menuBarOption1);
			menubar.setStyle(menuBarOption1);
			paneCenter.setStyle(paneOption1);
			anteLabel.setStyle("-fx-font-size: 20;" + "-fx-text-fill: red;");
			ppLabel.setStyle("-fx-font-size: 20;" + "-fx-text-fill: red;");
			playCardsLabel.setStyle("-fx-font-size: 20;" + "-fx-text-fill: red;");
			p1.setStyle(fontSize + "-fx-text-fill: red;");
			p2.setStyle(fontSize + "-fx-text-fill: red;");
			submit.setStyle(buttonSize + buttonOption1);
			play.setStyle(buttonSize + buttonOption1);
			fold.setStyle(buttonSize + buttonOption1);
			playAgain.setStyle(buttonSize + buttonOption1);
			quitGame.setStyle(buttonSize + buttonOption1);
		}
		else {
			menu.setStyle(menuBarOption2);
			menubar.setStyle(menuBarOption2);
			paneCenter.setStyle(paneOption2);
			anteLabel.setStyle("-fx-font-size: 20;" + "-fx-text-fill: red;");
			ppLabel.setStyle("-fx-font-size: 20;" + "-fx-text-fill: red;");
			playCardsLabel.setStyle("-fx-font-size: 20;" + "-fx-text-fill: red;");
			p1.setStyle(fontSize + "-fx-text-fill: red;");
			p2.setStyle(fontSize + "-fx-text-fill: red;");
			submit.setStyle(buttonSize + buttonOption2);
			play.setStyle(buttonSize + buttonOption2);
			fold.setStyle(buttonSize + buttonOption2);
			playAgain.setStyle(buttonSize + buttonOption2);
			quitGame.setStyle(buttonSize + buttonOption2);
		}
		
		m1.setOnAction(e -> System.exit(0) );
		m2.setOnAction(e -> {
			turn = 1;
			playerOne = new Player();
			playerTwo = new Player();
			theDealer = new Dealer();
			stage.setScene(playGameScene());
		});
		m3.setOnAction(e -> {
			if(option == 0) {
				option = 1;
				menu.setStyle(menuBarOption2);
				menubar.setStyle(menuBarOption2);
				paneCenter.setStyle(paneOption2);
				submit.setStyle(buttonSize + buttonOption2);
				play.setStyle(buttonSize + buttonOption2);
				fold.setStyle(buttonSize + buttonOption2);
				playAgain.setStyle(buttonSize + buttonOption1);
				quitGame.setStyle(buttonSize + buttonOption1);
			}
			
			else {
				option = 0;
				menu.setStyle(menuBarOption1);
				menubar.setStyle(menuBarOption1);
				paneCenter.setStyle(paneOption1);
				submit.setStyle(buttonSize + buttonOption1);
				play.setStyle(buttonSize + buttonOption1);
				fold.setStyle(buttonSize + buttonOption1);
				playAgain.setStyle(buttonSize + buttonOption2);
				quitGame.setStyle(buttonSize + buttonOption2);
			}
		});
		
		//Setup scoreBoard data up here
		queueItems = FXCollections.observableArrayList();
		ArrayList<String> dataArr = fillArrayListData();
		for(String s: dataArr) {
			queueItems.add(s);
		}
		p1Stat = new ListView<String>();
		p1Stat.setItems(queueItems);
		p1Stat.setPrefWidth(150);
		p1Stat.setPrefHeight(305);
		VBox stat = new VBox(p1Stat);
		
		//Game starts here for Part 1
		//This helps setup card deck in corner
		card_back = new Image("CardBack.png");
		card_backV = new ImageView(card_back);
		card_backV.setFitHeight(150);
		card_backV.setFitWidth(150);
		card_backV.setPreserveRatio(true);
		HBox cardBackImg = new HBox(card_backV);
		cardBackImg.setPadding(new Insets(50,50,50,50));
		
		//This puts stat and cardback deck in the same top pane
		topBox = new HBox(stat, cardBackImg);
		VBox overallTop = new VBox(mBar, topBox);
		
		//This sets up the part on bottom for player to input bets
		anteBox = new HBox(50, anteLabel, anteBet);
		ppBox = new HBox(12, ppLabel, ppBet);
		txtBox = new VBox(anteBox, ppBox);
		bottomInfo = new HBox(50,p1, txtBox, submit);
		playerCardBox = new HBox();
		
		bottomInfo.setAlignment(Pos.CENTER);
		
		//The majority of the work for part 1 and 2 of the game
		submit.setOnAction( e -> {
			if((!anteBet.getText().trim().isEmpty() && !ppBet.getText().trim().isEmpty()) && turn == 1) {
				//Grab info from text boxes and put into List box
				int anteBetNum = Integer.parseInt(anteBet.getText());
				int ppBetNum = Integer.parseInt(ppBet.getText());
				if((anteBetNum >= 5 && anteBetNum <= 25) &&
					((ppBetNum >= 5 && ppBetNum <= 25) || ppBetNum == 0) ) {
					
					playerOne.setAnteBet(anteBetNum);
					playerOne.setPairPlusBet(ppBetNum);
					
					//Update list
					ArrayList<String> dataArr2 = fillArrayListData();
					p1Stat.getItems().removeAll(queueItems);
					queueItems.clear();
					for(String s: dataArr2) {
						queueItems.add(s);
					}
					p1Stat.setItems(queueItems);
					
					//Change turn to 2
					turn = 2;
					
					//Reset textbox
					anteBet.setText("0");
					ppBet.setText("0");
					
					//Change Player 1 label
					bottomInfo.getChildren().remove(0);
					bottomInfo.getChildren().add(0, p2);
					msg.setText("Place a bet between $5 to $25 for ante and pair plus(optional)");
				}
				
				else {
					msg.setText("Bet MUST be within $5 to $25 dollars.\n"
							+ "Pair Plus bet can be $0 or between $5 to $25");
				}
				
			}
			
			else if((!anteBet.getText().trim().isEmpty() && !ppBet.getText().trim().isEmpty()) && turn == 2) {
				//Grab info from text boxes and put into List box
				int anteBetNum = Integer.parseInt(anteBet.getText());
				int ppBetNum = Integer.parseInt(ppBet.getText());
				if((anteBetNum >= 5 && anteBetNum <= 25) &&
					((ppBetNum >= 5 && ppBetNum <= 25) || ppBetNum == 0) ) {
					
					playerTwo.setAnteBet(anteBetNum);
					playerTwo.setPairPlusBet(ppBetNum);
					
					//Update list
					ArrayList<String> dataArr2 = fillArrayListData();
					p1Stat.getItems().removeAll(queueItems);
					queueItems.clear();
					for(String s: dataArr2) {
						queueItems.add(s);
					}
					p1Stat.setItems(queueItems);
					
					//Change turn to 2
					turn = 3;
					
					//Clear textBox
					anteBet.clear();
					ppBet.clear();
					
					//Disables not needed things for next part
					bottomInfo.setVisible(false);
					msg.setVisible(false);
					
					//Deal the cards
					playerOne.setHand(theDealer.dealHand());
					playerTwo.setHand(theDealer.dealHand());
					theDealer.setDealersHand(theDealer.dealHand());
					
					//This displays player 1 cards
					showP1Cards();
					
					//Whole playerCard box
					playerCardBox = new HBox(200, p2Box, p1Box);
					playerCardBox.setAlignment(Pos.CENTER);
					playerCardBox.setPadding(new Insets(200,0,0,0));
					
					//whole dealerCard box
					allCardsBox = new VBox(dealerCardBox, playerCardBox);
					allCardsBox.setAlignment(Pos.CENTER);
					paneCenter.setCenter(allCardsBox);
					
					//Play and fold buttons
					playFoldBox = new VBox(20, play, fold);
					playCards = new TextField("0");
					
					HBox playCardsBetBox = new HBox(playCardsLabel, playCards);
					playFoldMsg = new Label("Place a bet between $5 to $25 or fold.");
					playFoldMsg.setStyle("-fx-font-size: 15;" + "-fx-text-fill: red;");
					msgAndTxt = new VBox(playCardsBetBox, playFoldMsg);
					msgAndTxt.setPadding(new Insets(45,0,0,0));
					
					bottomPlayFoldBox = new HBox(50, p1, msgAndTxt, playFoldBox);
					bottomPlayFoldBox.setAlignment(Pos.CENTER);
					paneCenter.setBottom(bottomPlayFoldBox);
				}
				else {
					msg.setText("Bet MUST be within $5 to $25 dollars.\n"
							+ "Pair Plus bet can be $0 or between $5 to $25");
				}	
			}
		});
		
		//Part 3
		play.setOnAction(e -> {
			//Player 1 turn to bet
			if(turn == 3) {
				if(!playCards.getText().trim().isEmpty()) {
					//Checks if player bet between 5 to 25 dollars
					int playBetNum = Integer.parseInt(playCards.getText());
					if(playBetNum == playerOne.getAnteBet()) {
						
						playerOne.setPlayBet(playBetNum);
						
						//Update list
						ArrayList<String> dataArr2 = fillArrayListData();
						p1Stat.getItems().removeAll(queueItems);
						queueItems.clear();
						for(String s: dataArr2) {
							queueItems.add(s);
						}
						p1Stat.setItems(queueItems);
						
						//Change turn to 4
						turn = 4;
						
						//Reset textbox
						playCards.setText("0");
						
						//Change Player 1 label
						bottomPlayFoldBox.getChildren().remove(0);
						bottomPlayFoldBox.getChildren().add(0, p2);
						
						//This displays player 2 cards
						showP2Cards();
						
						//Whole playerCard box
						playerCardBox = new HBox(200, p2Box, p1Box);
						playerCardBox.setAlignment(Pos.CENTER);
						playerCardBox.setPadding(new Insets(200,0,0,0));
						
						//whole dealerCard box
						allCardsBox = new VBox(dealerCardBox, playerCardBox);
						allCardsBox.setAlignment(Pos.CENTER);
						paneCenter.setCenter(allCardsBox);
						
						playFoldMsg.setText("Place a bet between $5 to $25 or fold.");
					}
					else {
						playFoldMsg.setText("Play bet MUST be the same as ante bet.");
					}
				}
			}
			//Player 2 turn to bet
			else if(turn == 4) {
				if(!playCards.getText().trim().isEmpty()) {
					//Checks if player bet between 5 to 25 dollars
					int playBetNum = Integer.parseInt(playCards.getText());
					if(playBetNum == playerTwo.getAnteBet()) {
						
						playerTwo.setPlayBet(playBetNum);
						
						//Update list
						ArrayList<String> dataArr2 = fillArrayListData();
						p1Stat.getItems().removeAll(queueItems);
						queueItems.clear();
						for(String s: dataArr2) {
							queueItems.add(s);
						}
						p1Stat.setItems(queueItems);
						
						//Change turn to 5
						turn = 5;
						
						//Reset textbox
						playCards.setText("0");
						
						//This displays all cards
						showAllCards();
						
						//Whole playerCard box
						playerCardBox = new HBox(200, p2Box, p1Box);
						playerCardBox.setAlignment(Pos.CENTER);
						playerCardBox.setPadding(new Insets(200,0,0,0));
						
						//whole dealerCard box
						allCardsBox = new VBox(dealerCardBox, playerCardBox);
						allCardsBox.setAlignment(Pos.CENTER);
						paneCenter.setCenter(allCardsBox);
						
						//Evaluates Cards
						setupEndGame();
						
						//Update list
						dataArr2 = fillArrayListData();
						p1Stat.getItems().removeAll(queueItems);
						queueItems.clear();
						for(String s: dataArr2) {
							queueItems.add(s);
						}
						p1Stat.setItems(queueItems);
						endGameBottom.setAlignment(Pos.CENTER);
						paneCenter.setBottom(endGameBottom);
					}
					else {
						playFoldMsg.setText("Play bet MUST be the same as ante bet.");
					}
				}
			}
		});
		
		fold.setOnAction(e -> {
			//Player 1 turn to bet
			if(turn == 3) {
				playerOne.setDidFold(true);
				
				//Change turn to 4
				turn = 4;
				
				//Reset textbox
				playCards.setText("0");
				
				//Change Player 1 label
				bottomPlayFoldBox.getChildren().remove(0);
				bottomPlayFoldBox.getChildren().add(0, p2);
				
				//This displays player 2 cards
				showP2Cards();
				
				//Whole playerCard box
				playerCardBox = new HBox(200, p2Box, p1Box);
				playerCardBox.setAlignment(Pos.CENTER);
				playerCardBox.setPadding(new Insets(200,0,0,0));
				
				//whole dealerCard box
				allCardsBox = new VBox(dealerCardBox, playerCardBox);
				allCardsBox.setAlignment(Pos.CENTER);
				paneCenter.setCenter(allCardsBox);
				
				playFoldMsg.setText("Place a bet between $5 to $25 or fold.");
			}
			else if(turn == 4) {
				playerTwo.setDidFold(true);
				
				//Change turn to 4
				turn = 5;
				
				//Reset textbox
				playCards.setText("0");
				
				//Change Player 1 label
				bottomPlayFoldBox.getChildren().remove(0);
				bottomPlayFoldBox.getChildren().add(0, p2);
				
				//This displays player 2 cards
				showAllCards();
				
				//Whole playerCard box
				playerCardBox = new HBox(200, p2Box, p1Box);
				playerCardBox.setAlignment(Pos.CENTER);
				playerCardBox.setPadding(new Insets(200,0,0,0));
				
				//whole dealerCard box
				allCardsBox = new VBox(dealerCardBox, playerCardBox);
				allCardsBox.setAlignment(Pos.CENTER);
				paneCenter.setCenter(allCardsBox);
				
				//Evaluates Cards
				setupEndGame();
				
				//Update list
				ArrayList<String> dataArr2 = fillArrayListData();
				p1Stat.getItems().removeAll(queueItems);
				queueItems.clear();
				for(String s: dataArr2) {
					queueItems.add(s);
				}
				p1Stat.setItems(queueItems);
				endGameBottom.setAlignment(Pos.CENTER);
				paneCenter.setBottom(endGameBottom);
			}
		});
		
		quitGame.setOnAction(e->{
			System.exit(0);
		});
		
		playAgain.setOnAction(e->{
			playerOne.setAnteBet(0);
			playerOne.setDidFold(false);
			playerOne.setPairPlusBet(0);
			playerOne.setPlayBet(0);
			playerTwo.setAnteBet(0);
			playerTwo.setDidFold(false);
			playerTwo.setPairPlusBet(0);
			playerTwo.setPlayBet(0);
			
			turn = 1;
			stage.setScene(playGameScene());
			
		});
		
		paneCenter.setTop(overallTop);
		paneCenter.setCenter(msg);
		paneCenter.setBottom(bottomInfo);
		paneCenter.setLeft(stat);
		paneCenter.setRight(cardBackImg);
		paneCenter.setPadding(new Insets(0,0,50,0));
		
		return new Scene(paneCenter, 1200, 750);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Let's Play Three Card Poker!!!");
		stage = primaryStage;
		
		//Set up Player and Dealer
		playerOne = new Player();
		playerTwo = new Player();
		theDealer = new Dealer();
		
		primaryStage.setScene(StartScene());
		primaryStage.show();
	}

}
