import org.junit.Test;
import static org.junit.Assert.*;

public class JUnitPileTest {

    // Test to make sure that the topCard of the Pile is the last card played
    @Test
    public void testPileTopCard(){
        Deck testDeck = new Deck();
        Card testCard = testDeck.deal();
        Pile testPile = new Pile(testCard);
        testCard = testDeck.deal();
        testPile.addCard(testCard);
        System.out.println("Pile Top Card = Last Card Added");
        System.out.println(testPile.getTopCard() + " = " + testCard);
        assertEquals(testPile.getTopCard(), testCard);
    }
}
