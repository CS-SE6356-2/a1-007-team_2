import java.util.ArrayList;

public class Pile {

    // attributes for Pile class
    ArrayList<Card> discardPile = new ArrayList<>(52);
    int numberOfCards;

    // Constructor
    public Pile(Card starter){
        discardPile.add(starter);
        numberOfCards = discardPile.size();
    }

    public Card getTopCard(){
        Card card = discardPile.get(numberOfCards);
        return card;
    }


}
