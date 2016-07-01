package model;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * A parser for the Buienradar XML API.
 *
 * @author Hendrik Werner
 */
public final class BuienradarParser implements WeatherInfoProvider {

    private static final String API_ENDPOINT = "http://xml.buienradar.nl/";
    private static final String STATION_TAG = "weerstation";

    private final ListProperty<WeatherInfo> weatherInfo;
    private DocumentBuilder builder;

    public BuienradarParser() {
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            weatherInfo = new SimpleListProperty<>(FXCollections.observableArrayList());
            refresh();
        } catch (ParserConfigurationException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ListProperty<WeatherInfo> weatherInfoProperty() {
        return weatherInfo;
    }

    @Override
    public WeatherInfoProvider refresh() {
        try {
            weatherInfo.clear();
            Document doc = builder.parse(new URL(API_ENDPOINT).openStream());
            NodeList stations = doc.getElementsByTagName(STATION_TAG);
            for (int i = 0; i < stations.getLength(); i++) {
                weatherInfo.add(new WeatherInfo((Element) stations.item(i)));
            }
        } catch (IOException | SAXException | ParseException ex) {
            throw new RuntimeException(ex);
        }
        return this;
    }

}
