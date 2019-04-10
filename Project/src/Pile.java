import java.util.Stack;

public class Pile {

    // attributes for Pile class
    public Stack<Card> discardPile = new Stack<>();

    // Constructor
    public Pile(Card starter){
        discardPile.push(starter);
    }

    // Returns top card of discard pile
    public Card getTopCard(){
        Card card = discardPile.peek();
        return card;
    }

    // Adds card to top of discard pile
    public void addCard(Card c){
        Card topCard = getTopCard();
        if(c.getValue().equals("8")){
            discardPile.push(c);
        }
        else if(c.getSuit().equals(topCard.getSuit()) || c.getValue().equals(getTopCard().getValue())){
            discardPile.push(c);
        }
        else{   //Card is not playable
            System.out.println("Card is not playable. Please make another choice.");
            // TODO Add method to draw a card from the deck
        }
    }
}
