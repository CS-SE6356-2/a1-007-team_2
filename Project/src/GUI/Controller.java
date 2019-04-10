package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Controller {

    @FXML
    public Label playerOneName;

    public String playerName;

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
    public void menuNewGame() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewGameDialog.fxml"));
        Parent parent = loader.load();
        NewGameDialogController dialogController = loader.getController();
        dialogController.setPlayerName(playerName);
        Stage stage = new Stage();
        stage.setTitle("New Game");
        stage.setScene(new Scene(parent));
        stage.show();
    }
}