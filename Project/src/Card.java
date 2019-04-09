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

    // Get Card value
    public String getValue() {
        return cardValue;
    }

    // Override Method for Card.toString()
    // Prints the suit and value of a Card object
    @Override
    public String toString(){
        return (this.getValue() + " " + this.getSuit());
    }

}
