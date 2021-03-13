import java.util.ArrayList;


//This class deals with the dealer.
//It has a deck and it's own hand for the game
//It can deal cards out to the players also
public class Dealer {
	
	//Private variables
	private Deck theDeck;
	private ArrayList<Card> dealersHand;
	
	//Constructor for Dealer
	Dealer(){
		theDeck = new Deck();
		dealersHand = new ArrayList<Card>();
	}
	
	//Returns the deck
	public Deck getDeck() {
		return theDeck;
	}
	
	//Returns the dealersHand
	public ArrayList<Card> getDealersHand(){
		return dealersHand;
	}
	
	//setter for the deck
	public void setDeck(Deck d) {
		theDeck = d;
	}
	
	//setter for the dealersHand
	public void setDealersHand(ArrayList<Card> hand) {
		dealersHand = hand;
	}
	
	//This deals the hand. It returns an arraylist of top 3 cards.
	//If the deck size is <= 34, it will reshuffle the deck
	public ArrayList<Card> dealHand(){
		if(theDeck.size() <= 34) {
			theDeck.newDeck();
		}
		
		return returnCards();
	}
	
	//This is a private utility function that returns top 3 cards of deck
	//and removes them from the deck
	private ArrayList<Card> returnCards() {
		ArrayList<Card> temp = new ArrayList<Card>();
		for(int i = 0; i < 3; i++)
			temp.add(theDeck.remove(0));
		return temp;
	}
	
	
}
