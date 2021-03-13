import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ThreeCardTest {

	ArrayList<Card> dealer;
	ArrayList<Card> playerOne;
	
	@BeforeEach
	void init() {
		dealer = new ArrayList<Card>();
		playerOne = new ArrayList<Card>();
	}
	
	//This tests to see if pairs are identified correctly
	@Test
	void testEvalHandPairs() {
		playerOne.add(new Card('S', 13));
		playerOne.add(new Card('S', 10));
		playerOne.add(new Card('H', 13));
		
		assertEquals(ThreeCardLogic.evalHand(playerOne), 5, "Pair not identified correctly for evalHand().");
	}
	
	//This tests if flushes are identified correctly
	@Test
	void testEvalHandFlush() {
		playerOne.add(new Card('S', 2));
		playerOne.add(new Card('S', 14));
		playerOne.add(new Card('S', 8));
		
		assertEquals(ThreeCardLogic.evalHand(playerOne), 4, "Flush not identified correctly for evalHand().");
	}
	
	//This tests if straight work correctly for normal cases not on edge
	@Test
	void testEvalHandStraight() {
		playerOne.add(new Card('S', 14));
		playerOne.add(new Card('H', 12));
		playerOne.add(new Card('S', 13));
		
		assertEquals(ThreeCardLogic.evalHand(playerOne), 3, "Straight not identified correctly for evalHand().");
	}
	
	//This tests specifically if A,2,3 works
	@Test
	void testEvalHandStraightEdge() {
		playerOne.add(new Card('S', 2));
		playerOne.add(new Card('C', 14));
		playerOne.add(new Card('H', 3));
		
		assertEquals(ThreeCardLogic.evalHand(playerOne), 3, "A,2,3 straight not identified correctly for evalHand().");
	}
	
	//This tests to see if three of a kind works
	@Test
	void testEvalHandThreeOfKind() {
		playerOne.add(new Card('S', 12));
		playerOne.add(new Card('C', 12));
		playerOne.add(new Card('H', 12));
		
		assertEquals(ThreeCardLogic.evalHand(playerOne), 2, "Three of a kind not identified correctly for evalHand().");
	}
	
	//This tests if straight flush works on normal cases not on edge
	@Test
	void testEvalHandStraightFlush() {
		playerOne.add(new Card('C', 12));
		playerOne.add(new Card('C', 14));
		playerOne.add(new Card('C', 13));
		
		assertEquals(ThreeCardLogic.evalHand(playerOne), 1, "Straight flush not identified correctly for evalHand().");
	}
	
	//This tests if straight flush works on specific case A,2,3
	@Test
	void testEvalHandStraightFlushEdge() {
		playerOne.add(new Card('C', 2));
		playerOne.add(new Card('C', 14));
		playerOne.add(new Card('C', 3));
		
		assertEquals(ThreeCardLogic.evalHand(playerOne), 1, "Straight flush not identified correctly for evalHand() on A,2,3.");
	}
	
	//This tests for a high hand 
	@Test
	void testEvalHandHighCard() {
		playerOne.add(new Card('C', 2));
		playerOne.add(new Card('H', 5));
		playerOne.add(new Card('C', 14));
		
		assertEquals(ThreeCardLogic.evalHand(playerOne), 0, "High Hand not identified correctly for evalHand().");
	}
	
	//This tests ppWinnings to see if returns right amount for pairs
	@Test
	void testEvalPPWinningsPairs() {
		playerOne.add(new Card('C', 2));
		playerOne.add(new Card('C', 14));
		playerOne.add(new Card('H', 2));
		
		assertEquals(ThreeCardLogic.evalPPWinnings(playerOne, 5), 10, "evalPPWinnings wrong for pairs");
	}
	
	//This tests ppWinnings to see if returns right amount for flush
	@Test
	void testEvalPPWinningsFlush() {
		playerOne.add(new Card('H', 5));
		playerOne.add(new Card('H', 14));
		playerOne.add(new Card('H', 2));
		
		assertEquals(ThreeCardLogic.evalPPWinnings(playerOne, 5), 20, "evalPPWinnings wrong for flush");
	}
	
	//This tests ppWinnings to see if returns right amount for straight
	@Test
	void testEvalPPWinningsStraight() {
		playerOne.add(new Card('S', 8));
		playerOne.add(new Card('C', 6));
		playerOne.add(new Card('H', 7));
		
		assertEquals(ThreeCardLogic.evalPPWinnings(playerOne, 5), 35, "evalPPWinnings wrong for straight");
	}
	
	//This tests ppWinnings to see if returns right amount for Three of a Kind
	@Test
	void testEvalPPWinningsThreeOfKind() {
		playerOne.add(new Card('C', 2));
		playerOne.add(new Card('S', 2));
		playerOne.add(new Card('H', 2));
		
		assertEquals(ThreeCardLogic.evalPPWinnings(playerOne, 5), (30*5)+5, "evalPPWinnings wrong for three of a kind");
	}
	
	//This tests ppWinnings to see if returns right amount for Straight Flush
	@Test
	void testEvalPPWinningsStraightFlush() {
		playerOne.add(new Card('S', 14));
		playerOne.add(new Card('S', 3));
		playerOne.add(new Card('S', 2));
		
		assertEquals(ThreeCardLogic.evalPPWinnings(playerOne, 5), (40*5)+5, "evalPPWinnings wrong for straight flush");
	}
	
	//This tests compareHands function for pairs specifically if dealer hand is equal type to player hand
	//and the have the same hands
	@Test
	void testCompareHandsPairEqualSameHand() {
		playerOne.add(new Card('S', 14));
		playerOne.add(new Card('H', 14));
		playerOne.add(new Card('S', 3));
		dealer.add(new Card('H', 14));
		dealer.add(new Card('S', 3));
		dealer.add(new Card('C', 14));
		
		assertEquals(ThreeCardLogic.compareHands(dealer, playerOne), 0, "Comparing hands with both pairs and same card value doesn't work");
	}
	
	//This tests compareHands function for pairs specifically if dealer hand is equal type to player hand
	//and player has the higher hand
	@Test
	void testCompareHandsPairEqualHigherHand() {
		playerOne.add(new Card('S', 14));
		playerOne.add(new Card('H', 14));
		playerOne.add(new Card('S', 5));
		dealer.add(new Card('H', 14));
		dealer.add(new Card('S', 3));
		dealer.add(new Card('C', 14));
		
		assertEquals(2 , ThreeCardLogic.compareHands(dealer, playerOne), "Comparing hands with both pairs and player has higher card value doesn't work");
	}
	
	//This tests compareHands function for pairs specifically if dealer hand is equal type to player hand
	//and player has the lower hand
	@Test
	void testCompareHandsPairEqualLowerHand() {
		playerOne.add(new Card('S', 14));
		playerOne.add(new Card('H', 14));
		playerOne.add(new Card('S', 2));
		dealer.add(new Card('H', 14));
		dealer.add(new Card('S', 3));
		dealer.add(new Card('C', 14));
		
		assertEquals(1, ThreeCardLogic.compareHands(dealer, playerOne), "Comparing hands with both pairs and player has lower card value doesn't work");
	}
	
	//This tests compareHands function for when dealer has pair and player has flush
	@Test
	void testCompareHandsPairHighHand() {
		playerOne.add(new Card('S', 14));
		playerOne.add(new Card('C', 2));
		playerOne.add(new Card('H', 5));
		dealer.add(new Card('H', 14));
		dealer.add(new Card('S', 3));
		dealer.add(new Card('C', 14));
		
		assertEquals(1, ThreeCardLogic.compareHands(dealer, playerOne), "Comparing hands dealer with pairs and player with high hand. Dealer should win");
	}
	
	//This tests compareHands function for when dealer has pair and player has straight flush
	@Test
	void testCompareHandsPairStraightFlush() {
		playerOne.add(new Card('C', 14));
		playerOne.add(new Card('C', 12));
		playerOne.add(new Card('C', 13));
		dealer.add(new Card('H', 14));
		dealer.add(new Card('S', 3));
		dealer.add(new Card('C', 14));
		
		assertEquals(2, ThreeCardLogic.compareHands(dealer, playerOne), "Comparing hands dealer with pairs and player with straight flush. Player should win.");
	}
	
	//This tests compareHands function for flush specifically if dealer hand is equal type to player hand
	//and the have the same hands
	@Test
	void testCompareHandsFlushEqualSameHand() {
		playerOne.add(new Card('S', 9));
		playerOne.add(new Card('S', 7));
		playerOne.add(new Card('S', 3));
		dealer.add(new Card('S', 9));
		dealer.add(new Card('S', 7));
		dealer.add(new Card('S', 3));
		
		assertEquals(ThreeCardLogic.compareHands(dealer, playerOne), 0, "Comparing hands with both flush and same card value doesn't work");
	}
	
	//This tests compareHands function for flush specifically if dealer hand is equal type to player hand
	//and player has the higher hand
	@Test
	void testCompareHandsFlushEqualHigherHand() {
		playerOne.add(new Card('S', 9));
		playerOne.add(new Card('S', 14));
		playerOne.add(new Card('S', 7));
		dealer.add(new Card('S', 9));
		dealer.add(new Card('S', 3));
		dealer.add(new Card('S', 7));
		
		assertEquals(2 , ThreeCardLogic.compareHands(dealer, playerOne), "Comparing hands with both flush and player has higher card value doesn't work");
	}
	
	//This tests compareHands function for flush specifically if dealer hand is equal type to player hand
	//and player has the lower hand
	@Test
	void testCompareHandsFlushEqualLowerHand() {
		playerOne.add(new Card('S', 3));
		playerOne.add(new Card('S', 7));
		playerOne.add(new Card('S', 2));
		dealer.add(new Card('S', 14));
		dealer.add(new Card('S', 3));
		dealer.add(new Card('S', 7));
		
		assertEquals(1, ThreeCardLogic.compareHands(dealer, playerOne), "Comparing hands with both flush and player has lower card value doesn't work");
	}
	
	//This tests compareHands function for when dealer has flush and player has high hand
	@Test
	void testCompareHandsFlushHighHand() {
		playerOne.add(new Card('C', 3));
		playerOne.add(new Card('S', 2));
		playerOne.add(new Card('H', 5));
		dealer.add(new Card('S', 14));
		dealer.add(new Card('S', 3));
		dealer.add(new Card('S', 7));
		
		assertEquals(1, ThreeCardLogic.compareHands(dealer, playerOne), "Comparing hands dealer with flush and player with high hand. Dealer should win");
	}
	
	//This tests compareHands function for when dealer has flush and player has straight flush
	@Test
	void testCompareHandsFlushStraightFlush() {
		playerOne.add(new Card('C', 14));
		playerOne.add(new Card('C', 12));
		playerOne.add(new Card('C', 13));
		dealer.add(new Card('S', 14));
		dealer.add(new Card('S', 3));
		dealer.add(new Card('S', 7));
		
		assertEquals(2, ThreeCardLogic.compareHands(dealer, playerOne), "Comparing hands dealer with flush and player with straight flush. Player should win.");
	}
	
	//This tests compareHands function for straight specifically if dealer hand is equal type to player hand
	//and the have the same hands
	@Test
	void testCompareHandsStraightEqualSameHand() {
		playerOne.add(new Card('S', 9));
		playerOne.add(new Card('H', 7));
		playerOne.add(new Card('S', 8));
		dealer.add(new Card('S', 9));
		dealer.add(new Card('H', 7));
		dealer.add(new Card('S', 8));
		
		assertEquals(ThreeCardLogic.compareHands(dealer, playerOne), 0, "Comparing hands with both straight and same card value doesn't work");
	}
	
	//This tests compareHands function for straight specifically if dealer hand is equal type to player hand
	//and player has the higher hand
	@Test
	void testCompareHandsStraightEqualHigherHand() {
		playerOne.add(new Card('S', 10));
		playerOne.add(new Card('H', 9));
		playerOne.add(new Card('S', 8));
		dealer.add(new Card('S', 9));
		dealer.add(new Card('H', 8));
		dealer.add(new Card('S', 7));
		
		assertEquals(2 , ThreeCardLogic.compareHands(dealer, playerOne), "Comparing hands with both straight and player has higher card value doesn't work");
	}
	
	//This tests compareHands function for straight specifically if dealer hand is equal type to player hand
	//and player has the lower hand
	@Test
	void testCompareHandsStraightEqualLowerHand() {
		playerOne.add(new Card('S', 8));
		playerOne.add(new Card('H', 7));
		playerOne.add(new Card('S', 6));
		dealer.add(new Card('S', 9));
		dealer.add(new Card('H', 8));
		dealer.add(new Card('S', 7));
		
		assertEquals(1, ThreeCardLogic.compareHands(dealer, playerOne), "Comparing hands with both straight and player has lower card value doesn't work");
	}
	
	//This tests compareHands function for when dealer has straight and player has high hand
	@Test
	void testCompareHandsStraightHighHand() {
		playerOne.add(new Card('C', 3));
		playerOne.add(new Card('S', 2));
		playerOne.add(new Card('H', 5));
		dealer.add(new Card('S', 14));
		dealer.add(new Card('H', 13));
		dealer.add(new Card('S', 12));
		
		assertEquals(1, ThreeCardLogic.compareHands(dealer, playerOne), "Comparing hands dealer with straight and player with high hand. Dealer should win");
	}
	
	//This tests compareHands function for when dealer has straight and player has straight flush
	@Test
	void testCompareHandsStraight_StraightFlush() {
		playerOne.add(new Card('C', 14));
		playerOne.add(new Card('C', 12));
		playerOne.add(new Card('C', 13));
		dealer.add(new Card('S', 14));
		dealer.add(new Card('H', 13));
		dealer.add(new Card('S', 12));
		
		assertEquals(2, ThreeCardLogic.compareHands(dealer, playerOne), "Comparing hands dealer with straight and player with straight flush. Player should win.");
	}
	
	//This tests compareHands function for three of kind specifically if dealer hand is equal type to player hand
	//and the have the same hands
	@Test
	void testCompareHandsThreeEqualSameHand() {
		playerOne.add(new Card('S', 9));
		playerOne.add(new Card('C', 9));
		playerOne.add(new Card('H', 9));
		dealer.add(new Card('S', 9));
		dealer.add(new Card('H', 9));
		dealer.add(new Card('C', 9));
		
		assertEquals(ThreeCardLogic.compareHands(dealer, playerOne), 0, "Comparing hands with both three of kind and same card value doesn't work");
	}
	
	//This tests compareHands function for three of kind specifically if dealer hand is equal type to player hand
	//and player has the higher hand
	@Test
	void testCompareHandsThreeEqualHigherHand() {
		playerOne.add(new Card('S', 10));
		playerOne.add(new Card('H', 10));
		playerOne.add(new Card('C', 10));
		dealer.add(new Card('S', 9));
		dealer.add(new Card('H', 9));
		dealer.add(new Card('C', 9));
		
		assertEquals(2 , ThreeCardLogic.compareHands(dealer, playerOne), "Comparing hands with both three of kind and player has higher card value doesn't work");
	}
	
	//This tests compareHands function for three of kind specifically if dealer hand is equal type to player hand
	//and player has the lower hand
	@Test
	void testCompareHandsThreeEqualLowerHand() {
		playerOne.add(new Card('S', 3));
		playerOne.add(new Card('H', 3));
		playerOne.add(new Card('C', 3));
		dealer.add(new Card('S', 7));
		dealer.add(new Card('H', 7));
		dealer.add(new Card('C', 7));
		
		assertEquals(1, ThreeCardLogic.compareHands(dealer, playerOne), "Comparing hands with both three of kind and player has lower card value doesn't work");
	}
	
	//This tests compareHands function for when dealer has three of kind and player has high hand
	@Test
	void testCompareHandsThreeHighHand() {
		playerOne.add(new Card('C', 3));
		playerOne.add(new Card('S', 2));
		playerOne.add(new Card('H', 5));
		dealer.add(new Card('S', 14));
		dealer.add(new Card('H', 14));
		dealer.add(new Card('C', 14));
		
		assertEquals(1, ThreeCardLogic.compareHands(dealer, playerOne), "Comparing hands dealer with three of kind and player with high hand. Dealer should win");
	}
	
	//This tests compareHands function for when dealer has three of kind and player has straight flush
	@Test
	void testCompareHandsThreeStraightFlush() {
		playerOne.add(new Card('C', 14));
		playerOne.add(new Card('C', 12));
		playerOne.add(new Card('C', 13));
		dealer.add(new Card('S', 7));
		dealer.add(new Card('H', 7));
		dealer.add(new Card('C', 7));
		
		assertEquals(2, ThreeCardLogic.compareHands(dealer, playerOne), "Comparing hands dealer with three of kind and player with straight flush. Player should win.");
	}
	
	//This tests compareHands function for Straight flush specifically if dealer hand is equal type to player hand
	//and the have the same hands
	@Test
	void testCompareHandsStraightFlushEqualSameHand() {
		playerOne.add(new Card('S', 9));
		playerOne.add(new Card('S', 7));
		playerOne.add(new Card('S', 8));
		dealer.add(new Card('S', 9));
		dealer.add(new Card('S', 7));
		dealer.add(new Card('S', 8));
		
		assertEquals(ThreeCardLogic.compareHands(dealer, playerOne), 0, "Comparing hands with both straight flush and same card value doesn't work");
	}
	
	//This tests compareHands function for straight flush specifically if dealer hand is equal type to player hand
	//and player has the higher hand
	@Test
	void testCompareHandsStraightFlushEqualHigherHand() {
		playerOne.add(new Card('S', 9));
		playerOne.add(new Card('S', 10));
		playerOne.add(new Card('S', 11));
		dealer.add(new Card('S', 9));
		dealer.add(new Card('S', 8));
		dealer.add(new Card('S', 7));
		
		assertEquals(2 , ThreeCardLogic.compareHands(dealer, playerOne), "Comparing hands with both straight flush and player has higher card value doesn't work");
	}
	
	//This tests compareHands function for straight flush specifically if dealer hand is equal type to player hand
	//and player has the lower hand
	@Test
	void testCompareHandsStraightFlushEqualLowerHand() {
		playerOne.add(new Card('S', 3));
		playerOne.add(new Card('S', 4));
		playerOne.add(new Card('S', 5));
		dealer.add(new Card('S', 8));
		dealer.add(new Card('S', 9));
		dealer.add(new Card('S', 7));
		
		assertEquals(1, ThreeCardLogic.compareHands(dealer, playerOne), "Comparing hands with both straight flush and player has lower card value doesn't work");
	}
	
	//This tests compareHands function for when dealer has straight flush and player has high hand
	@Test
	void testCompareHandsStraightFlushHighHand() {
		playerOne.add(new Card('C', 3));
		playerOne.add(new Card('S', 2));
		playerOne.add(new Card('H', 5));
		dealer.add(new Card('S', 14));
		dealer.add(new Card('S', 13));
		dealer.add(new Card('S', 12));
		
		assertEquals(1, ThreeCardLogic.compareHands(dealer, playerOne), "Comparing hands dealer with straight flush and player with high hand. Dealer should win");
	}
	
	//This tests high hand dealer queen exception
	@Test
	void testHighHandQueenException() {
		playerOne.add(new Card('S', 14));
		playerOne.add(new Card('S', 13));
		playerOne.add(new Card('S', 12));
		dealer.add(new Card('S', 2));
		dealer.add(new Card('H', 9));
		dealer.add(new Card('C', 6));
		
		assertEquals(0, ThreeCardLogic.compareHands(dealer, playerOne), "Dealer doesn't have a queen higher so bet should be returned.");
	}
	
	//This test if compare hand returns 0 if both hands are high hand and is the same hand
	@Test
	void testHighHandSameHand() {
		playerOne.add(new Card('S', 14));
		playerOne.add(new Card('H', 2));
		playerOne.add(new Card('C', 12));
		dealer.add(new Card('S', 14));
		dealer.add(new Card('H', 2));
		dealer.add(new Card('C', 12));
		
		assertEquals(0, ThreeCardLogic.compareHands(dealer, playerOne), "Did not correctly return when dealer and player have high hand and same value cards");
	}
	
	//This tests compare hand returns 1 if player has the lower high hand
	@Test
	void testHighHandLowerCard() {
		playerOne.add(new Card('S', 10));
		playerOne.add(new Card('H', 2));
		playerOne.add(new Card('C', 12));
		dealer.add(new Card('S', 14));
		dealer.add(new Card('H', 2));
		dealer.add(new Card('C', 12));
		
		assertEquals(1, ThreeCardLogic.compareHands(dealer, playerOne), "Did not correctly return when dealer and player have high hand and dealer has higher value card");
	}
	
	//This tests compare hand returns 2 if player has higher high hand
	@Test
	void testLowerHandCard() {
		playerOne.add(new Card('S', 14));
		playerOne.add(new Card('H', 2));
		playerOne.add(new Card('C', 12));
		dealer.add(new Card('S', 10));
		dealer.add(new Card('H', 2));
		dealer.add(new Card('C', 12));
		
		assertEquals(2, ThreeCardLogic.compareHands(dealer, playerOne), "Did not correctly return when dealer and player have high hand and dealer has higher value card");
	}

}
