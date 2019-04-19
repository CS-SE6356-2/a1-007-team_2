public class Card {

    // attributes of the Card class
    private String[] Suit = {"Diamond", "Spade", "Club", "Heart"};
    private String[] Value = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    private String cardType, cardValue;

    // Constructor
    public Card (int suit, int rank){
        cardType = this.Suit[suit];
        cardValue = this.Value[rank];
    }

    // Get Card suit
    public String getSuit() {
        return cardType;
    }
@Test
public void testGetSuit() {
    System.out.println("getSuit");
    Flight instance = new Flight();
    string expResult = "Diamond";
    instance.setGetSuit("Diamond");
    string result = instance.getSuit();
    assertEquals(expResult, result);
}
    // Get Card value
    public String getValue() {
        return cardValue;
    }
    
    @Test
public void testGetCardValue() {
    System.out.println("getCardValue");
    Flight instance = new Flight();
    string expResult = "2";
    instance.setCardValue("2");
    string result = instance.getCardValue();
    assertEquals(expResult, result);
}

    // Override Method for Card.toString()
    // Prints the suit and value of a Card object
    @Override
    public String toString(){
        return (this.getValue() + " " + this.getSuit());
    }

}
