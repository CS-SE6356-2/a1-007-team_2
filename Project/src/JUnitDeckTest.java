import org.junit.Test;

public class JUnitDeckTest {

    // Test to see if the Deck is properly shuffled
    @Test
    public void testDeckShuffle() {
        Deck testDeck = new Deck();
        testDeck.shuffle();
        testDeck.printCards();
    }

}
