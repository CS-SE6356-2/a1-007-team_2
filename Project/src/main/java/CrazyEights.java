import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CrazyEights {

    private String chosenSuit;

    // Constructor for the Main.CrazyEights game Class
    public CrazyEights(){
    }
        // Start a Single Player Game
        public ArrayList<Player> startLocalGame(String playerName) {
            ArrayList<Player> playerList = new ArrayList<>(4);
            playerList.add(new Player(playerName));
            playerList.add(new Player("Two"));
            playerList.add(new Player("Three"));
            playerList.add(new Player("Four"));
            return playerList;
    }

    // Creates a Deck for the game and deals to each of the players
    public Deck gameDeck(ArrayList<Player> players) {
        Deck deck = new Deck();
        deck.shuffle();
        deck.dealToPlayers(players, deck);
        return deck;
    }

    // Creates a Discard Pile to be used during the game and updates the UI
    public Pile stockPile(Deck deck, Controller controller){
        Pile stockPile = new Pile(deck.deal());
        controller.updatePile(stockPile.getTopCard());
        this.chosenSuit = stockPile.getTopCard().getSuit();
        return stockPile;
    }

    // Adds a playable card to the Pile and updates the player hand and UI
    private void playCard(Player player, Card playCard, Pile pile, Controller controller){
        pile.addCard(playCard);
        controller.updatePile(playCard);
        player.getHand().remove(playCard);
        controller.updatePlayerCards();
    }

    // Checks for a winner at the end of each players turn
    private void checkForWinner(Player player){
        if (player.getHand().size() == 0){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Player "+player.getPlayerName()+" WINS!");
            System.exit(0);
        }
    }

    // Method to choose suit of value to Pile
    private void playEight(Pile pile, Controller controller){
        Card card = new Card();
        int i = 0;
        String[] choices = {"Diamond", "Spade", "Club", "Heart", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "Jack", "Queen", "King", "Ace"};


        JFrame frame = new JFrame();
        String result = JOptionPane.showInputDialog(frame, "Enter selected suit or value: \n i.e. King, Club, 5, " +
                "Spade, Ace, 10");

        // determine suit to play based on option selected
        // then add a dummy card to the pile

        for (String selection: choices){
            if (result.equals(selection)) {
                card = card.dummyCard(i);
            }
            i++;
        }

        pile.addCard(card);
        controller.playersChoice(card);

        //If the return value was null/empty.
        //setLabel("Please choose a suit.");
    }

    // If the Deck is empty at the end of the players turn method calculateScore()
    // is called to see which player has the least amount of points based on cards in hand
    public void calculateScores(ArrayList<Player> players){
        JPanel frame = new JPanel();
        String scoreMessage = "";
        for (Player player:players){
            scoreMessage = scoreMessage.concat("Player " + player.getPlayerName() +
                    " Score:" + player.getHandScore() + "\n");
        }
        JOptionPane.showMessageDialog(frame, scoreMessage);
    }

    // When a card is picked from the UI the turnPlay() method is called
    // This will check if the card is playable then update the UI accordingly
    public void turnPlay(int cardIndex, ArrayList<Player> players, Deck deck, Pile pile, Controller controller) {
        Player player = players.get(0);
        Card playCard = player.getHand().get(cardIndex);

        if (playCard.getValue().equals("8")){
            playCard(player, playCard, pile, controller);
            playEight(pile, controller);
            AIPlay(players, deck, pile, controller);
        } else if(playCard.getSuit().equals(pile.getTopCard().getSuit()) || playCard.getValue().equals(pile.getTopCard().getValue())){
            playCard(player, playCard, pile, controller);
            AIPlay(players, deck, pile, controller);
        } else {
            JOptionPane.showMessageDialog(null, "Card is not Playable. Please Choose Another Card.");
        }

        checkForWinner(player);

    }

    private boolean isPlayable(Card c, Pile top){
        if(c.getSuit().equals(chosenSuit)||c.getValue().equals(top.getTopCard().getValue())){
            return true;
        }
        return false;
    }

    // At the end of the user's turn the method AIPlay() is called to update
    // the pile if the AI player has a playable card then update the UI
    private void AIPlay(ArrayList<Player> players, Deck deck, Pile stockPile, Controller controller){
        Card topPile = stockPile.getTopCard();
        int playableCard = 0;
        boolean hasPlayable = false;

        // loop through AI players cards to see if they
        // have a card that can be played on the pile
        for (int i = 1; i < 3; i++){
            // Pause to mimic an AI player making their choice; 5-10 seconds
//            Random rand = new Random();
//            int sleepTime = rand.nextInt(5);
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch(InterruptedException e) {
//                System.out.println("Error! Player "+i+" was interrupted!");
//            }


            ArrayList<Card> playerCards = players.get(i).getHand();
            for (Card c : playerCards){
                if (c.getSuit().equals(topPile.getSuit())||c.getValue().equals(topPile.getValue())) {
                    hasPlayable = true;
                    playableCard = players.get(i).getHand().indexOf(c);
                } else {
                    hasPlayable = false;
                }
            }

            // if a playable exists in the players hand
            // the Card is added to the Pile and the UI is updated
            if (hasPlayable){
                controller.updatePile(players.get(i).getHand().get(playableCard));
                stockPile.addCard(players.get(i).getHand().get(playableCard));
                players.get(i).getHand().remove(playableCard);
            } else {

                if (deck.getCardList().size() > 0){
                    players.get(i).getHand().add(deck.deal());

                } else {
                    calculateScores(players);
                }
            }

            checkForWinner(players.get(i));

        }
    }
}
