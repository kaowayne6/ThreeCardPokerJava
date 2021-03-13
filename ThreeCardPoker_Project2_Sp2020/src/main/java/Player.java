import java.util.ArrayList;

//The player class keeps track of all the values needed for bets to be calculated
//It has ante and pairplusBets which is assigned in the beginning of the game,
//playbet which is assigned in the middle of the game
//and totalwnings and money which is reassigned at the end of the game
public class Player {
	private ArrayList<Card> hand;
	private int anteBet;
	private int playBet;
	private int pairPlusBet;
	private int totalWinnings;
	private int totalMoney;
	private boolean didFold;
	
	Player(){
		hand = new ArrayList<Card>();
		anteBet = 0;
		playBet = 0;
		pairPlusBet = 0;
		totalWinnings = 0;
		totalMoney = 500;
		didFold = false;
	}
	
	//Getter for hand
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	//Getter for anteBet
	public int getAnteBet(){
		return anteBet;
	}
	
	//Getter for playBet
	public int getPlayBet(){
		return playBet;
	}
	
	//Getter for pairPlusBet
	public int getPairPlusBet(){
		return pairPlusBet;
	}
	
	//Getter for totalWinnings
	public int getTotalWinnings(){
		return totalWinnings;
	}
	
	public int getTotalMoney() {
		return totalMoney;
	}
	
	public boolean getDidFold() {
		return didFold;
	}
	
	//Setter for hand
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	//Setter for anteBet
	public void setAnteBet(int anteBet) {
		this.anteBet = anteBet;
	}
	
	//Setter for playBet
	public void setPlayBet(int playBet) {
		this.playBet = playBet;
	}

	//Setter for pairPlusBet
	public void setPairPlusBet(int pairPlusBet) {
		this.pairPlusBet = pairPlusBet;
	}
	
	//Setter for totalWinnings
	public void setTotalWinnings(int totalWinnings) {
		this.totalWinnings = totalWinnings;
	}
	
	public void setTotalMoney(int money) {
		this.totalMoney = money;
	}
	
	public void setDidFold(boolean fold) {
		didFold = fold;
	}
}
