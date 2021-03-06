import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {

    // attributes of Player class
    private String playerName;
    private ArrayList<Card> playerHand = new ArrayList<>(5);
    private int playerIndex;

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

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    // Get players hand score
    public int getHandScore(){
        int score = 0;
        List numbers = Arrays.asList("2", "3", "4", "5", "6", "7", "9", "10");
        List royal = Arrays.asList("A", "K", "Q", "J");
        for (Card card:playerHand){
            String value = card.getValue();
            if (numbers.contains(value)){
                score = score + Integer.parseInt(value);
            } else if (royal.contains(value)){
                if (value.equals("A")){
                    score = score + 1;
                } else {
                    score = score + 10;
                }
            } else {
                score = score + 50;
            }
        }
        return score;
    }

    // Prints cards in the Players hand
    // Used for Testing
    public void showHand(){
        System.out.println(this.playerHand);
    }

}
