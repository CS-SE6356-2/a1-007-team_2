import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javax.swing.*;
import java.util.ArrayList;


public class Controller{

    // Link components from the fxml file
    @FXML private Label playerOneName;
    @FXML private ImageView pileImg;
    @FXML private ImageView playerOneHand;
    @FXML private GridPane playerHandGrid1;
    @FXML private GridPane playerHandGrid2;
    @FXML private GridPane playerHandGrid3;
    @FXML private GridPane mainGrid;

    // Instance attributes used for the game
    private CrazyEights game;
    private ArrayList<Player> players;
    private Deck deck;
    private Pile pile;

    // Menu bar close event
    @FXML
    public void menuClose(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Quit?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            System.exit(0);
        }
    }

    // Menu bar option to start a new game.
    // Opens the dialog box to initiate a game
    @FXML
    public void menuNewGame(){
        JFrame frame = new JFrame();
        String input = JOptionPane.showInputDialog(frame, "Enter your name:");
        playerOneName.setText(input);
        gameType();
    }

    // User selects the type of game to play
    // then initializes the game
    private void gameType(){

        // Displays a popup widow to choose the game type
        JFrame frame = new JFrame();
        String[] choices = {"Single Player Game", "Multi-player Game"};
        int result = JOptionPane.showOptionDialog(
                frame, "Choose the type of game to play: ", "Game Type",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                choices, choices[0]);

        // If Option to play a Single Player game is selected Initialize the game
        if (result == JOptionPane.YES_OPTION){
            this.game = new CrazyEights();
            this.players = game.startLocalGame(playerOneName.getText());
            this.deck = game.gameDeck(this.players);
            this.pile = game.stockPile(this.deck, Controller.this);
            mainGrid.getChildren().remove(playerOneHand);
            updatePlayerCards();
        } else {
            // Run Multiplayer Game
        }
    }

    // Updates the UI with the hands of the users hand and sets
    // the eventHandlers for the cards displayed on the UI
    public void updatePlayerCards() {
        ArrayList<Card> cards = players.get(0).getHand();
        GridPane cardGrid;

        // clears the hand so that is can be redrawn after a card is played
        playerHandGrid1.getChildren().clear();
        playerHandGrid2.getChildren().clear();
        playerHandGrid3.getChildren().clear();
        int i = 0;

        // Loop through the cards in the users hand and display the card on the screen
        for (Card c:cards){

            // get the users cards and choose the correct image to display
            String playCard = c.getValue()+c.getSuit().substring(0,1);
            String imagePath = String.valueOf(getClass().getResource("images/cards/" + playCard + ".png"));
            Image pic = new Image(imagePath);
            ImageView image = new ImageView(pic);
            image.setFitHeight(143);
            image.setFitWidth(111);

            // eventHandler to play a card once it is clicked
            image.setOnMouseClicked(event -> game.turnPlay(cards.indexOf(c), players,
                    deck, pile, Controller.this));

            // Draws the hand in the order of the cards in the players hand
            // If statement to decide which row to display the cards on
            if (cards.indexOf(c) < 6) {
                cardGrid = playerHandGrid1;
                playerHandGrid2.setVisible(false);
                playerHandGrid3.setVisible(false);
            } else if (cards.indexOf(c) < 12) {
                playerHandGrid2.setVisible(true);
                playerHandGrid3.setVisible(false);
                cardGrid = playerHandGrid2;
            } else {
                playerHandGrid3.setVisible(true);
                cardGrid = playerHandGrid3;
            }

            // Draws card on the UI
            cardGrid.addColumn(i, image);
            i++;
        }
    }

    // Method to draw a card from the deck when the deck image
    // is clicked on the UI window
    @FXML
    public void draw(){
        if (deck.getCardList().size() == 0){
            game.calculateScores(players);
        } else {
            players.get(0).getHand().add(deck.deal());
            updatePlayerCards();
        }
    }

    // Updates the the UI with the most recent card played
    public void updatePile(Card card){
        String playCard = card.getValue()+card.getSuit().substring(0,1);
        String imagePath = String.valueOf(getClass().getResource("images/cards/" + playCard + ".png"));
        Image pic = new Image(imagePath);
        pileImg.setImage(pic);
    }

}