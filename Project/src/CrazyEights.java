import java.util.ArrayList;

public class CrazyEights {

    public static void main(String[] args) {

        // start the game
        playGame();

    }

    // Start the Game
    public static void playGame(){
        ArrayList<Player> playerList = new ArrayList<>(4);
        Player playerOne = new Player("One");
        Player playerTwo = new Player("Two");
        playerList.add(playerOne);
        playerList.add(playerTwo);

        Deck deck = new Deck();
        deck.shuffle();

        for (Player p: playerList) {
            ArrayList<Card> playerHand = deck.dealToPlayer();
            p.setHand(playerHand);
        }

        Pile stockPile = new Pile(deck.deal());
        getTopCard();

        
        playerOne.showHand();
        playerTwo.showHand();

        getTopCard();
        
        // Player One takes turn.
        playerOne.addcard();
      
        // Player two takes turn.

        playerTwo.addcard();
    
    
     }

}
