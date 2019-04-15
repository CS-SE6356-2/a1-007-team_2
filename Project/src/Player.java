import java.util.ArrayList;

public class Player {

    // attributes of Player class
    private String playerName;
    private ArrayList<Card> playerHand = new ArrayList<>(5);

    // Constructor
    public Player(String name){
        this.playerName = name;
    }

    // Set a ArrayList of Card objects as the players hand
    public ArrayList<Card> getHand(){
        return playerHand;
    }

    // Get player name
    public String getPlayerName() {
        return playerName;
    }

    // Prints cards in the Players hand
    // Used for Testing
    public void showHand(){
        System.out.println(this.playerHand);
    }

}
