package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NewGameDialogController extends Controller{

    @FXML TextField yourNameInput;
    public String name;

    @FXML
    public void getPlayerName() {
        this.name = yourNameInput.getText();
        System.out.println(name);
    }

    public void setPlayerName(String playerName){
        this.name = playerName;
    }
}
