import java.util.ArrayList;
import java.util.Collections;


//This deck is an arraylist that creates a deck of 52 Cards
//It has a function newDeck that reshuffles a new set of 52 cards into the Deck
public class Deck extends ArrayList<Card>{
	
	Deck(){
		char[] suits = {'C', 'D', 'S', 'H'};
		for(int s = 0; s < 4; s++) {
			
			for(int i = 2; i <= 14; i++) {
				this.add(new Card(suits[s], i));
			}
		
		}
		
		Collections.shuffle(this);
	}
	
	public void newDeck(){
		this.clear();
		
		char[] suits = {'C', 'D', 'S', 'H'};
		for(int s = 0; s < 4; s++) {
			
			for(int i = 2; i <= 14; i++) {
				this.add(new Card(suits[s], i));
			}
		
		}
		
		Collections.shuffle(this);
		
	}
}
