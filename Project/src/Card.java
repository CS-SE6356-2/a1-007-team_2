public class Card {

    private String[] Suit = {"Diamond", "Spade", "Club", "Heart"};
    private String[] Value = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    private String cardType, cardValue;

    public Card (int suit, int rank){
       cardType = this.Suit[suit];
       cardValue = this.Value[rank];
    }

    public String getSuit() {
        return cardType;
    }

    public String getValue() {
        return cardValue;
    }

}
