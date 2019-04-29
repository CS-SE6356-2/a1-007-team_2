import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;
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

    // display the game rules when the user selects the About Crazy Eights option from the menu
    @FXML private void rules(){
        JOptionPane.showMessageDialog(null, "Object of the Game\n" +
                "The goal is to be the first player to get rid of all the cards in their hand.\n\n" +
                "Card Values/Scoring\n" +
                "The player who is the first to have no cards left wins the game. \nThe winning player collects from " +
                "each other player the value of the cards remaining in that player’s hand as follows:\n" +
                "Each eight = 50 points\n" +
                "Each K, Q, J or 10 = 10 points\n" +
                "Each ace = 1 point\n" +
                "Each other card is the face value\n\n" +
                "The Play\n" +
                "Starting with player one, the next player must place one card face up on the starter pile.\n" +
                "Each card played (other than an eight) must match the card showing on the starter pile, either in " +
                "suit or in value.\n" +
                "If unable to play, the player draws one card from the deck and the turn is passed to the next player.\n"+
                "All eights are wild. That is, an eight may be played at any time in turn, and the player need only " +
                "specify a suit or value. \nThe next player must play either a card of the specified suit, value, or " +
                "an eight.", "Crazy Eight Rules", JOptionPane.PLAIN_MESSAGE, null);
    }

    @FXML private void contact(){
        JLabel message = new JLabel("<html><center>If there are any issues while using this program "+
                "please contact the members of<br>Group 2 from SE 3345.007 - Spring 2019:<br><br>" +
                "Nicholas Malzac: nrm170630@UTDallas.edu<br>" +
                "Martha Bridges: mmb160330@UTDallas.edu<br>" +
                "Valeria Grimaldo: vag170000@UTDallas.edu<br>" +
                "Dylan Capece: dsc160330@UTDallas.edu");
        JOptionPane.showMessageDialog(null, message, "Contact Information", JOptionPane.PLAIN_MESSAGE, null );
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
            game = new CrazyEights();
            players = game.startLocalGame(playerOneName.getText());
            deck = game.gameDeck(this.players);
            pile = game.stockPile(this.deck, Controller.this);
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
            image.setOnMouseClicked(event -> {game.turnPlay(cards.indexOf(c), players,
                            deck, pile, Controller.this); });

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
            game.AIPlay(players, deck, pile, this);
        }
    }

    // Updates the the UI with the most recent card played
    public void updatePile(Card card, int playerIndex){
        String cardBack = String.valueOf(getClass().getResource("images/cards/back5.png"));
        Image cardBackImg = new Image(cardBack);
        ImageView cardFromHand = new ImageView();
        cardFromHand.setFitHeight(158);
        cardFromHand.setFitWidth(120);
        cardFromHand.setImage(cardBackImg);
        cardFromHand.toFront();
        int x = 0, y = 0;
        int gridRow = 0, gridCol = 0;
        int timer = 0;
        switch(playerIndex){
            case 1:
                x = -89;
                y = -178;
                gridRow = 4;
                gridCol = 4;
                timer = 1;
                break;
            case 2:
                x = 570;
                y = -63;
                gridRow = 3;
                gridCol = 0;
                timer = 2;
                break;
            case 3:
                x = 245;
                y = 346;
                gridRow = 0;
                gridCol = 2;
                timer = 3;
                break;
            case 4:
                x = -250;
                y = 220;
                gridRow = 1;
                gridCol = 5;
                timer = 4;
                break;
        }
        Line line = new Line(0,0,x,y);
        mainGrid.add(cardFromHand,gridCol,gridRow);
        PathTransition path = new PathTransition(Duration.seconds(timer), line);
        path.setNode(cardFromHand);
        path.setOnFinished(e -> {
            String playCard = card.getValue()+card.getSuit().substring(0,1);
            String imagePath = String.valueOf(getClass().getResource("images/cards/" + playCard + ".png"));
            Image pic = new Image(imagePath);
            pileImg.setImage(pic);
            cardFromHand.setVisible(false);
        });
        path.setAutoReverse(false);
        if (card.getValue().equals("8") & playerIndex == 1){
            cardFromHand.setVisible(false);
        } else {
            path.play();
        }
    }

    // Updates the the UI with the most recent card played
    public void playersChoice(Card card){
        String playCard;
        if (card.getValue() == null){
            playCard = card.getSuit();
        } else {
            playCard = card.getValue();
        }
        String imagePath = String.valueOf(getClass().getResource("images/cards/" + playCard + ".png"));
        Image pic = new Image(imagePath);
        pileImg.setImage(pic);
    }

}