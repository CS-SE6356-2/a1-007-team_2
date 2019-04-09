import org.junit.Test;
import static org.junit.Assert.*;

public class JUnitDeckTest {

    // Test to see if the Deck is properly shuffled
    @Test
    public void testDeckShuffle() {
        Deck testDeck = new Deck();
        Deck shuffledDeck = new Deck();
        shuffledDeck.shuffle();
        shuffledDeck.printCards();
        assertNotEquals(testDeck, shuffledDeck);
    }

}
