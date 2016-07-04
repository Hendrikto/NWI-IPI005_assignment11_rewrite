package assignment11;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.BuienradarParser;
import model.WeatherInfo;
import model.WeatherInfoProvider;
import model.WeatherInfoUpdater;

/**
 * FXML Controller class.
 *
 * @author Hendrik Werner
 */
public class InterfaceController implements Initializable {

    private WeatherInfoProvider parser;

    @FXML private Text weatherInfoText;
    @FXML private ImageView weatherInfoImage;
    @FXML private ChoiceBox<WeatherInfo> stationChoice;

    /**
     * Initialize the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        parser = new BuienradarParser();
        stationChoice.itemsProperty().bind(parser.weatherInfoProperty());
        stationChoice.getSelectionModel().selectedItemProperty().addListener((ov, v, nv) -> {
            if (stationChoice.getValue() != null) {
                refreshUI();
            }
        });
        stationChoice.getSelectionModel().selectFirst();
    }

    @FXML
    private void refreshData() {
        new Thread(new WeatherInfoUpdater(parser, stationChoice.getSelectionModel())).start();
    }

    private void refreshUI() {
        WeatherInfo info = stationChoice.getValue();
        weatherInfoImage.setImage(new Image(info.getIconURL()));
        weatherInfoText.setText(info.getInfo());
    }

}
