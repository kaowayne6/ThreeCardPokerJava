import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class DeckTest {
	
	Deck d;
	Dealer deal;
	
	//Initialize Deck and dealer object
	@BeforeEach
	void init() {
		d = new Deck();
		deal = new Dealer();
	}
	
	//Tests deck if initialized properly
	@Test
	void testDeckInitialization() {
		assertEquals(d.getClass().getName(), "Deck", "Deck class did not initialize correctly.");
	}
	
	//Test dealer Deck if that is initialized properly
	@Test
	void testDealerDeckInitialization() {
		assertEquals(deal.getDeck().getClass().getName(), "Deck", "Deck is not initialized correctly in Dealer class");
	}
	
	//Tests that dealer arrayList initialized properly
	@Test
	void testDealerArrayListInitialization() {
		assertEquals(deal.getDealersHand().getClass().getName(), "java.util.ArrayList", "ArrayList is not initialized correctly in dealer class.");
	}
	
	//Tests that deck Card was initialized properly
	@Test
	void testCardInitialization() {
		assertEquals(d.get(0).getClass().getName(), "Card", "Card is not intialized correctly.");
	}
	
	//Tests the reshuffle of cards
	@RepeatedTest(3)
	void testDeckRandomize() {
		ArrayList<Card> temp = new ArrayList<Card>();
		char[] suits = {'C', 'D', 'S', 'H'};
		for(int s = 0; s < 4; s++) {
			for(int i = 2; i <= 14; i++) {
				temp.add(new Card(suits[s], i));
			}
		}
		
		boolean isSame = true;
		for(int i = 0; i < temp.size(); i++) {
			if(temp.get(i).getSuit() != d.get(i).getSuit() ||
				temp.get(i).getValue() != d.get(i).getValue()) {
				isSame = false;
				break;
			}
		}
		
		assertFalse(isSame, "Deck is not randomized");
	}
	
	//Tests the new Deck function
	@RepeatedTest(3)
	void testNewDeck() {
		ArrayList<Card> temp = new ArrayList<Card>();
		for(int i = 0; i < 52; i++) {
			temp.add(new Card(d.get(i).getSuit(), d.get(i).getValue()));
		}
		
		d.newDeck();
		
		boolean isSame = true;
		for(int i = 0; i < temp.size(); i++) {
			if(temp.get(i).getSuit() != d.get(i).getSuit() ||
				temp.get(i).getValue() != d.get(i).getValue()) {
				isSame = false;
				break;
			}
		}
		
		assertFalse(isSame, "Deck is not randomized");
	}
	
	//Test that deal hand correctly deals the first 3 cards
	@Test
	void testDealHand() {
		ArrayList<Card> firstThree = new ArrayList<Card>();
		for(int i = 0; i < deal.getDeck().size(); i++) {
			firstThree.add(deal.getDeck().get(i));
		}
		
		ArrayList<Card> testDeal = deal.dealHand();
		assertEquals(testDeal.get(0).getValue(), firstThree.get(0).getValue(), "Cards not dealt correctly");
		assertEquals(testDeal.get(0).getSuit(), firstThree.get(0).getSuit(), "Cards not dealt correctly");
		assertEquals(testDeal.get(1).getValue(), firstThree.get(1).getValue(), "Cards not dealt correctly");
		assertEquals(testDeal.get(1).getSuit(), firstThree.get(1).getSuit(), "Cards not dealt correctly");
		assertEquals(testDeal.get(2).getValue(), firstThree.get(2).getValue(), "Cards not dealt correctly");
		assertEquals(testDeal.get(2).getSuit(), firstThree.get(2).getSuit(), "Cards not dealt correctly");	
	}
	
	//Tests that the next 3 cards after the first 3 cards are dealt correctly
	@Test
	void testDealHand2() {
		deal.dealHand();
		ArrayList<Card> firstThree = new ArrayList<Card>();
		for(int i = 0; i < deal.getDeck().size(); i++) {
			firstThree.add(deal.getDeck().get(i));
		}
		
		ArrayList<Card> testDeal = deal.dealHand();
		assertEquals(testDeal.get(0).getValue(), firstThree.get(0).getValue(), "Cards not dealt correctly");
		assertEquals(testDeal.get(0).getSuit(), firstThree.get(0).getSuit(), "Cards not dealt correctly");
		assertEquals(testDeal.get(1).getValue(), firstThree.get(1).getValue(), "Cards not dealt correctly");
		assertEquals(testDeal.get(1).getSuit(), firstThree.get(1).getSuit(), "Cards not dealt correctly");
		assertEquals(testDeal.get(2).getValue(), firstThree.get(2).getValue(), "Cards not dealt correctly");
		assertEquals(testDeal.get(2).getSuit(), firstThree.get(2).getSuit(), "Cards not dealt correctly");	
	}
	
	//This tests that the cards aren't reshuffled before 34 cards are dealt
	@Test
	void testDealHandBeforeShuffle() {
		deal.dealHand();
		deal.dealHand();
		deal.dealHand();
		deal.dealHand();
		deal.dealHand();
		deal.dealHand();
		assertEquals(deal.getDeck().size(), 34, "Cards were not dealt correctly.");
	}
	
	//This tests that the cards are reshuffled after more than 34 cards are dealt in a deck
	@Test
	void testDealHandAfterReshuffle() {
		deal.dealHand();
		deal.dealHand();
		deal.dealHand();
		deal.dealHand();
		deal.dealHand();
		deal.dealHand();
		deal.dealHand();
		assertEquals(49, deal.getDeck().size(), "Cards were not dealt correctly.");
	}
}
