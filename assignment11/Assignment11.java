package assignment11;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A weather app in JavaFX.
 *
 * @author Hendrik Werner
 */
public class Assignment11 extends Application {

    /**
     * Start the weather app.
     *
     * @param stage the primary stage
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Interface.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Weather App");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
