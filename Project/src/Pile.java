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

    // Returns top card of discard pile
    public Card getTopCard(){
        Card card = discardPile.get(numberOfCards);
        return card;
    }

    // Adds card to top of discard pile
    public void addCard(Card c){
        Card topCard = getTopCard();
        if(c.getValue() == "8"){
            discardPile.add(c);
            numberOfCards++;
        }
        else if(c.getSuit() == topCard.getSuit() || c.getValue() == getTopCard().getValue()){
            discardPile.add(c);
            numberOfCards++;
        }
        else{   //Card is not playable
            System.out.println("Card is not playable. Please make another choice.");
        }
    }
}
