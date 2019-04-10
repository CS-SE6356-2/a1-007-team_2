import java.util.ArrayList;

public class Deck {

    // attributes of the Deck class
    private ArrayList<Card> cardList;
    private int topCard;

    // Constructor
    public Deck() {
        cardList = new ArrayList<>(52);
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 13; j++){
                cardList.add(new Card(i, j));
            }
        }
    }

    // Method to shuffle the Deck instance
    public void shuffle() {
        ArrayList<Card> temp = new ArrayList<>();
        while(!cardList.isEmpty()) {
            int newInt = (int)(Math.random()*cardList.size());
            temp.add(cardList.get(newInt));
            cardList.remove(newInt);
        }
        cardList = temp;
    }

    // Method to deal five cards to a player from the Deck instance
    public ArrayList<Card> dealToPlayer(){
        ArrayList<Card> hand = new ArrayList<>(5);

        for(int i = 0; i < 5; i++){
            hand.add(cardList.remove(0));
        }

        return hand;
    }

    // Method to deal one card at a time
    public Card deal(){
        return cardList.remove(0);
    }

    // Print the cards in the instance of a Deck
    public void printCards() {
        int i = 1;
        for (Card c : cardList) {
            System.out.println((i++) + ": " + c.getValue() + " " + c.getSuit());
        }
    }
}