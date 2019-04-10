import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class JUnitPlayerTest {

    //Test to verify that a Player's hand is properly set
    @Test
    public void testSetHand(){
        Deck testDeck = new Deck();
        Player testPlayer = new Player("One");
        ArrayList<Card> testDeal = testDeck.dealToPlayer();
        testPlayer.setHand(testDeal);
        assertEquals(testPlayer.playerHand, testDeal);
    }
}
