import org.junit.Test;
import org.junit.Assert;


public class JUnitCardTest {

    @Test
    public void testGetSuit() {
        System.out.println("getSuit");
        Card instance = new Card(0, 0);
        String expResult = "Diamond";
        String result = instance.getSuit();
        Assert.assertEquals(expResult, result);
    }

    @Test
    public void testGetCardValue() {
        System.out.println("getCardValue");
        Card instance = new Card(0,0);
        String expResult = "2";
        String result = instance.getValue();
        Assert.assertEquals(expResult, result);
    }

}
