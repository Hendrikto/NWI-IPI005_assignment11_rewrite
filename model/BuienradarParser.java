package model;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * A parser for the Buienradar XML API.
 *
 * @author Hendrik Werner
 */
public final class BuienradarParser implements WeatherInfoProvider {

    private static final String API_ENDPOINT = "http://xml.buienradar.nl/";

    private final ListProperty<WeatherInfo> weatherInfoProperty;
    private ObservableList<WeatherInfo> weatherInfo;
    private DocumentBuilder builder;

    public BuienradarParser() {
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            refresh();
            weatherInfoProperty = new SimpleListProperty<>(weatherInfo);
        } catch (ParserConfigurationException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ListProperty<WeatherInfo> weatherInfoProperty() {
        return weatherInfoProperty;
    }

    @Override
    public void refresh() {
        try {
            weatherInfo = FXCollections.observableArrayList();
            NodeList stations = builder
                    .parse(new URL(API_ENDPOINT).openStream())
                    .getElementsByTagName(XmlTag.Station.name);
            for (int i = 0; i < stations.getLength(); i++) {
                weatherInfo.add(new WeatherInfo(stations.item(i)));
            }
        } catch (IOException | SAXException | ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void commit() {
        weatherInfoProperty.set(weatherInfo);
    }

    /**
     * An enumeration of Buienradar XML Tags
     */
    enum XmlTag {
        Station("weerstation"),
        StationName("stationnaam"),
        Date("datum"),
        Humidity("luchtvochtigheid"),
        Temperature("temperatuurGC"),
        WindSpeed("windsnelheidMS"),
        WindDirection("windrichting"),
        Gusts("windstotenMS"),
        AirPressure("luchtdruk"),
        Visibility("zichtmeters"),
        Rain("regenMMPU"),
        IconUrl("icoonactueel");

        public final String name;

        private XmlTag(String name) {
            this.name = name;
        }

    }

}
