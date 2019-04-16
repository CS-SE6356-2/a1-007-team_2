import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class JUnitPlayerTest {

    //Test to verify that a Player's hand is properly set
    @Test
    public void testSetHand(){
        Deck testDeck = new Deck();
        Player testPlayer = new Player("One");
        ArrayList<Card> testHand = new ArrayList<>(5);
        ArrayList<Player> testPlayerList = new ArrayList<>(4);

        for (int i = 0; i < 4; i++){
            testHand.add(testDeck.getCardList().get(i));
        }

        testPlayerList.add(testPlayer);
        testDeck.dealToPlayers(testPlayerList, testDeck);
        assertEquals(testPlayer.getHand(), testHand);
    }
}
