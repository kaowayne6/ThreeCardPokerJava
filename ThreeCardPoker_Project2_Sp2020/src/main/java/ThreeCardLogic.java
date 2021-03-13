import java.util.ArrayList;

public class ThreeCardLogic {
	
	//Issues with Three of the same suit not giving correct money
	
	//This evaluates the hand of a player or dealer
	//0 for high card
	//1 for straight flush
	//2 for three of a kind
	//3 for straight
	//4 for flush
	//5 for pair
	public static int evalHand(ArrayList<Card> hand) {
		//sort hand by smallest to highest
		for(int i = 0; i < hand.size()-1; i++) {
			int smallest = i;
			for(int j = i+1; j < hand.size(); j++) {
				if(hand.get(j).getValue() < hand.get(smallest).getValue()) {
					smallest = j;
				}
			}
			Card temp = hand.get(smallest);
			hand.set(smallest, hand.get(i));
			hand.set(i, temp);
		}
		
		Card c0 = hand.get(0);
		Card c1 = hand.get(1);
		Card c2 = hand.get(2);
		
		//Tests for straight flush- which tests for 3 numbers next to each other and same suit
		if((c0.getValue() == c1.getValue()-1) &&
			(c1.getValue() == c2.getValue()-1) &&
			(c0.getSuit() == c1.getSuit()) &&
			(c1.getSuit() == c2.getSuit())) {
			return 1;
		}
		
		//Tests for straight flush on edge(which is A,2,3 and same suit)
		else if(c0.getValue() == 2 &&
				c1.getValue() == 3 &&
				c2.getValue() == 14 &&
				c0.getSuit() == c1.getSuit() &&
				c1.getSuit() == c2.getSuit()) {
			return 1;
		}
		
		//Tests for three of a kind- which is three cards that are the same value
		else if(c0.getValue() == c1.getValue() && c1.getValue() == c2.getValue()) {
			return 2;
		}
		
		//Tests for straight- which is three cards next to each other
		else if((c0.getValue() == c1.getValue()-1) && (c1.getValue() == c2.getValue()-1)) {
			return 3;
		}
		
		//Tests for straight on edge(which is A,2,3)
		else if(c0.getValue() == 2 &&
				c1.getValue() == 3 &&
				c2.getValue() == 14) {
			return 3;
		}
		
		//Tests for flush- which is three of the same suit
		else if(c0.getSuit() == c1.getSuit() && c1.getSuit() == c2.getSuit()) {
			return 4;
		}
		
		//Tests for pair- which is two cards having the same value
		else if(c0.getValue() == c1.getValue() ||
				c0.getValue() == c2.getValue() ||
				c1.getValue() == c2.getValue()) {
			return 5;
		}
		else {
			return 0;
		}
	}
	
	//This function returns the amount of winnings for Pair Plus bonus
	//1x for pair
	//3x for flush
	//6x for straight
	//30x for three of a kind
	//40x for flush
	//Lose all if none of the above
	public static int evalPPWinnings(ArrayList<Card> hand, int bet) {
		//Tells what kind of hand a player has
		int pp = evalHand(hand);
		
		//Switch cases for each type of hand
		switch(pp){
			case 0:	//High hand
				return 0;
				
			case 1:	//flush
				return bet*40 + bet;
				
			case 2:	//three of a kind
				return bet*30+bet;
				
			case 3:	//straight
				return bet*6+bet;
				
			case 4:	//flush
				return bet*3+bet;
				
			case 5:	//pair
				return bet+bet;
				
			default:	//should never get to this case
				return 0;
		}
	}
	
	//This function is a helper function to compare hands that helps figure out
	//who has the higher valued card if they have the same type of hand
	//return 0 if the same
	//return 1 if dealer wins
	//return 2 if player wins
	private static int compareSameHandsHelper(ArrayList<Card> dealer, ArrayList<Card> player) {
		
		//sort dealer's hand by smallest to highest
		for(int i = 0; i < dealer.size()-1; i++) {
			int smallest = i;
			for(int j = i+1; j < dealer.size(); j++) {
				if(dealer.get(j).getValue() < dealer.get(smallest).getValue()) {
					smallest = j;
				}
			}
			Card temp = dealer.get(smallest);
			dealer.set(smallest, dealer.get(i));
			dealer.set(i, temp);
		}
		
		//sorts player hand by smallest to highest
		for(int i = 0; i < player.size()-1; i++) {
			int smallest = i;
			for(int j = i+1; j < player.size(); j++) {
				if(player.get(j).getValue() < player.get(smallest).getValue()) {
					smallest = j;
				}
			}
			Card temp = player.get(smallest);
			player.set(smallest, player.get(i));
			player.set(i, temp);
		}
		
		for(int i = 2; i >= 0; i--) {
			if(dealer.get(i).getValue() == player.get(i).getValue())
				continue;
			else if(dealer.get(i).getValue() > player.get(i).getValue())
				return 1;
			else
				return 2;
		}
		return 0;
	}
	
	//This function compares the hand of the dealer and player and returns:
	//0 if no one won
	//1 if dealer won
	//2 if player won
	public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
		//evaluates hand of both player
		int dealerHand = evalHand(dealer);
		int playerHand = evalHand(player);
		
		//If either hands are high cards
		if(dealerHand == 0 || playerHand == 0) {
			
			//sort dealer's hand by smallest to highest
			for(int i = 0; i < dealer.size()-1; i++) {
				int smallest = i;
				for(int j = i+1; j < dealer.size(); j++) {
					if(dealer.get(j).getValue() < dealer.get(smallest).getValue()) {
						smallest = j;
					}
				}
				Card temp = dealer.get(smallest);
				dealer.set(smallest, dealer.get(i));
				dealer.set(i, temp);
			}

			//if both hands are both 0, look for the hand with highest value
			//if both hand has same value, look for second highest then third
			//if both have same value hands, then draw
			//if dealer doesn't have a Queen or higher, return wager
			if(dealer.get(2).getValue() < 12 && dealerHand == 0) {
				return 0;
			}
			
			if(dealerHand == 0 && playerHand == 0) {
				return compareSameHandsHelper(dealer, player);
			}
			
			//Dealer has "high card" hand so player wins
			else if(dealerHand == 0)
				return 2;
			//player has "high card" hand so dealer wins
			else
				return 1;
		}
		
		//If both dealer and player have a pair
		else if(dealerHand == 5 && playerHand == 5) {
			//will store value of the pair card to compare later
			int valDouble1 = -1;
			int valDouble2 = -1;
			
			//checks which card is the double for player
			if(player.get(0).getValue() == player.get(1).getValue())	//1st and 2nd card
				valDouble1 = player.get(0).getValue();
			else if(player.get(0).getValue() == player.get(2).getValue())	//1st and 3rd card
				valDouble1 = player.get(0).getValue();
			else	//2nd and 3rd card
				valDouble1 = player.get(1).getValue();
			
			//checks which card is the double for dealer
			if(dealer.get(0).getValue() == dealer.get(1).getValue())	//1st and 2nd card
				valDouble2 = dealer.get(0).getValue();
			else if(dealer.get(0).getValue() == dealer.get(2).getValue())	//1st and 3rd card
				valDouble2 = dealer.get(0).getValue();
			else	//2nd and 3rd card
				valDouble2 = dealer.get(1).getValue();
			
			//If double card same value, checks for the last card to determine win
			if(valDouble1 == valDouble2) {
				//Tracks second highest card
				int secondHigh1 = -1;
				int secondHigh2 = -1;
				
				//Determines what the second highest card is for player
				for(Card c: player) {
					if(c.getValue() != valDouble1)
						secondHigh1 = c.getValue();
				}
				
				//Determines what the second highest card is for dealer
				for(Card c: dealer) {
					if(c.getValue() != valDouble2)
						secondHigh2 = c.getValue();
				}
				
				//SecondHigh card both equal
				if(secondHigh1 == secondHigh2) {
					return 0;
				}
				//Player has the higher second highest card
				else if(secondHigh1 > secondHigh2)
					return 2;
				//Dealer has the higher second highest card
				else
					return 1;
			}
			
			//Player has the higher card
			else if(valDouble1 > valDouble2) {
				return 2;
			}
			//Dealer has the higher card
			else
				return 1;
		}
		
		//neither has a high card 
		else {
			//if highest card is same, check for second highest
			if(dealerHand == playerHand)	
				return compareSameHandsHelper(dealer, player);
			//dealer has lower card
			else if(dealerHand > playerHand)
				return 2;
			//Player has lower card
			else
				return 1;
		}
	}
}
