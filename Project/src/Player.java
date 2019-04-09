import java.util.ArrayList;

public class Player {

    // attributes of Player class
    public String playerName;
    public ArrayList<Card> playerHand;

    // Constructor
    public Player(String name){
        this.playerName = name;
    }

    // Set a ArrayList of Card objects as the players hand
    public void setHand(ArrayList<Card> deckDeal){
        this.playerHand = deckDeal;
    }

    // Prints cards in the Players hand
    public void showHand(){
        System.out.println(this.playerHand);
    }

}
