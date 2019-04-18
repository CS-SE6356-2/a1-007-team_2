import java.util.Stack;

public class Pile {

    // attributes for Pile class
    private Stack<Card> discardPile = new Stack<>();

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
    public void addCard(Card card){
        discardPile.add(card);
    }
}
