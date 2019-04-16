import org.junit.Test;
import static org.junit.Assert.*;

public class JUnitDeckTest{

    // Test to see if the Deck is properly shuffled
    @Test
    public void testDeckShuffle() {
        Deck testDeck = new Deck();
        Deck shuffledDeck = new Deck();
        shuffledDeck.shuffle();
        System.out.println("Print Shuffled Cards:");
        shuffledDeck.printCards();
        assertNotEquals(testDeck, shuffledDeck);
    }

    @Test
    public void testDeal(){
        Deck testDeck = new Deck();
        Card topCard = testDeck.getCardList().get(0);
        Card dealtCard = testDeck.deal();
        System.out.println("Top Card = Card Dealt");
        System.out.println(topCard.toString() + " = " + dealtCard.toString());
        assertEquals(topCard, dealtCard);
    }

}
