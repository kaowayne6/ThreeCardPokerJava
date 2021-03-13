
//This class defines a card object which consists of a suit and value
//It has a constructor and get and set values
public class Card {
	//Private variables
	private char suit;
	private int value;
	
	//Constructor
	Card(char suit, int value){
		this.suit = suit;
		this.value = value;
	}
	
	//Getters and setters method
	public char getSuit() {
		return suit;
	}
	public int getValue() {
		return value;
	}
	public void setSuit(char suit) {
		this.suit = suit;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
