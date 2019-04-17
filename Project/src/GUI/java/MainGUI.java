import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainGUI extends Application {

    // Setup stage for the main game window
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("CrazyEightsGUI.fxml"));
        primaryStage.setTitle("Crazy Eights");
        Image anotherIcon = new Image("https://upload.wikimedia.org/wikipedia/en/thumb/4/47/C8_logo" +
                ".jpg/1200px-C8_logo.jpg");
        primaryStage.getIcons().add(anotherIcon);
        primaryStage.setScene(new Scene(root, 1000, 720));
        primaryStage.setMinHeight(720);
        primaryStage.setMinWidth(1000);
        primaryStage.setMaxHeight(720);
        primaryStage.setMaxWidth(1000);
        primaryStage.setOnCloseRequest(confirmCloseEventHandler);
        primaryStage.show();
    }

    // Launch the main window GUI
    public static void main(String[] args) {
        launch(args);
    }

    // Window Close Confirmation Dialog
    private EventHandler<WindowEvent> confirmCloseEventHandler = event -> {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Quit?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            System.exit(0);
        }
    };
}
