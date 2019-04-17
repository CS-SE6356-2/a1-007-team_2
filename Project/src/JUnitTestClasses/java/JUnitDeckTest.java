import org.junit.Assert;
import org.junit.Test;

public class JUnitDeckTest{

    // Test to see if the Main.Deck is properly shuffled
    @Test
    public void testDeckShuffle() {
        Deck testDeck = new Deck();
        Deck shuffledDeck = new Deck();
        shuffledDeck.shuffle();
        System.out.println("Print Shuffled Cards:");
        shuffledDeck.printCards();
        Assert.assertNotEquals(testDeck, shuffledDeck);
    }

    @Test
    public void testDeal(){
        Deck testDeck = new Deck();
        Card topCard = testDeck.getCardList().get(0);
        Card dealtCard = testDeck.deal();
        System.out.println("Top Main.Card = Main.Card Dealt");
        System.out.println(topCard.toString() + " = " + dealtCard.toString());
        Assert.assertEquals(topCard, dealtCard);
    }

}
