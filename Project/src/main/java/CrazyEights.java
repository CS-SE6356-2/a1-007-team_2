import javax.swing.*;
import java.util.ArrayList;

public class CrazyEights {

    private String chosenSuit;

    // Constructor for the Main.CrazyEights game Class
    public CrazyEights(){
    }
        // Start a Single Player Game
        public ArrayList<Player> startLocalGame(String playerName) {
            ArrayList<Player> playerList = new ArrayList<>(4);
            Player one = new Player(playerName);
            one.setPlayerIndex(1);
            Player two = new Player("Two");
            two.setPlayerIndex(2);
            Player three = new Player("Three");
            three.setPlayerIndex(3);
            Player four = new Player("Four");
            four.setPlayerIndex(4);

            playerList.add(one);
            playerList.add(two);
            playerList.add(three);
            playerList.add(four);
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
        controller.updatePile(stockPile.getTopCard(), 0);
        this.chosenSuit = stockPile.getTopCard().getSuit();
        return stockPile;
    }

    // Adds a playable card to the Pile and updates the player hand and UI
    private void playCard(Player player, Card playCard, Pile pile, Controller controller){
        pile.addCard(playCard);
        controller.updatePile(playCard, player.getPlayerIndex());
        player.getHand().remove(playCard);
        controller.updatePlayerCards();
    }

    // Checks for a winner at the end of each players turn
    private void checkForWinner(ArrayList<Player> players){
        for (Player p:players) {
            if (p.getHand().size() == 0) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Player " + p.getPlayerName() + " WINS!");
                calculateScores(players);
                break;
            }
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
        JOptionPane.showMessageDialog(frame, scoreMessage, "Player Scores", JOptionPane.PLAIN_MESSAGE,null);
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
        } else if(playCard.getSuit().equals(pile.getTopCard().getSuit()) ||
                playCard.getValue().equals(pile.getTopCard().getValue())){
            playCard(player, playCard, pile, controller);
            AIPlay(players, deck, pile, controller);
        } else {
            JOptionPane.showMessageDialog(null, "Card is not Playable. Please Choose Another Card.");
        }
    }

    private boolean isPlayable(Card c, Pile top){
        if(c.getSuit().equals(chosenSuit)||c.getValue().equals(top.getTopCard().getValue())){
            return true;
        }
        return false;
    }

    // At the end of the user's turn the method AIPlay() is called to update
    // the pile if the AI player has a playable card then update the UI
    public void AIPlay(ArrayList<Player> players, Deck deck, Pile stockPile, Controller controller){
         // loop through AI players cards to see if they
        // have a card that can be played on the pile
        for (int i = 1; i < 4; i++){
            int playableCard = 0;
            boolean hasPlayable = false;
            ArrayList<Card> playerCards = players.get(i).getHand();
            Card topPile = stockPile.getTopCard();
            for (Card c : playerCards){
                if (c.getSuit().equals(topPile.getSuit())||
                        c.getValue().equals(topPile.getValue())||
                        c.getValue().equals("8")) {
                    hasPlayable = true;
                    playableCard = players.get(i).getHand().indexOf(c);
                    break;
                } else {
                    hasPlayable = false;
                }
            }

            // if a playable exists in the players hand
            // the Card is added to the Pile and the UI is updated
            if (hasPlayable){
                controller.updatePile(players.get(i).getHand().get(playableCard), players.get(i).getPlayerIndex());
                stockPile.addCard(players.get(i).getHand().get(playableCard));
                players.get(i).getHand().remove(playableCard);
            } else {

                if (deck.getCardList().size() > 0){
                    players.get(i).getHand().add(deck.deal());
                } else {
                    checkForWinner(players);
                    break;
                }
            }
        }
        checkForWinner(players);
    }
}
