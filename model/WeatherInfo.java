package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Hold information about the weather at a station.
 *
 * @author Hendrik Werner
 */
public class WeatherInfo {

    private static final String DATE_FORMAT_XML = "MM/dd/yyyy HH:mm:ss";

    private final Date date;
    private final String stationName;
    private final String windDirection;
    private final String visibility;
    private final String airPressure;
    private final String rain;
    private final String humidity;
    private final String temp;
    private final String windSpeed;
    private final String gusts;
    private final String iconURL;

    /**
     * @param e the element that contains the tag
     * @param tag the tag that contains the content
     * @return the text content of the tag within the element
     */
    public static String getContent(Element e, String tag) {
        return e.getElementsByTagName(tag).item(0).getTextContent();
    }

    /**
     * @param station the dom element describing the station
     */
    public WeatherInfo(Element station) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_XML);
        date = dateFormat.parse(getContent(station, BuienradarParser.XmlTag.Date.name));
        stationName = getContent(station, BuienradarParser.XmlTag.StationName.name);
        humidity = getContent(station, BuienradarParser.XmlTag.Humidity.name);
        temp = getContent(station, BuienradarParser.XmlTag.Temperature.name);
        windSpeed = getContent(station, BuienradarParser.XmlTag.WindSpeed.name);
        gusts = getContent(station, BuienradarParser.XmlTag.Gusts.name);
        airPressure = getContent(station, BuienradarParser.XmlTag.AirPressure.name);
        windDirection = getContent(station, BuienradarParser.XmlTag.WindDirection.name);
        visibility = getContent(station, BuienradarParser.XmlTag.Visibility.name);
        rain = getContent(station, BuienradarParser.XmlTag.Rain.name);
        iconURL = getContent(station, BuienradarParser.XmlTag.IconUrl.name);
    }

    public WeatherInfo(Node station) throws ParseException {
        this((Element) station);
    }

    /**
     * @return the stationName
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the windDirection
     */
    public String getWindDirection() {
        return windDirection;
    }

    /**
     * @return the humidity
     */
    public String getHumidity() {
        return humidity + "%";
    }

    /**
     * @return the temp
     */
    public String getTemp() {
        return temp + "°C";
    }

    /**
     * @return the windSpeed
     */
    public String getWindSpeed() {
        return windSpeed + "m/s";
    }

    /**
     * @return the gusts
     */
    public String getGusts() {
        return gusts + "m/s";
    }

    /**
     * @return the airPressure
     */
    public String getAirPressure() {
        return airPressure + "hPa";
    }

    /**
     * @return the visibility
     */
    public String getVisibility() {
        return visibility + "m";
    }

    /**
     * @return the rain
     */
    public String getRain() {
        return rain + "mmpu";
    }

    /**
     * @return the iconURL
     */
    public String getIconURL() {
        return iconURL;
    }

    /**
     * @return a human-readable string representation
     */
    public String getInfo() {
        return String.join("\n",
                "Station: " + getStationName(),
                "Date: " + getDate(),
                "Temperature: " + getTemp(),
                "Rain: " + getRain(),
                "Wind Speed: " + getWindSpeed(),
                "Wind Direction: " + getWindDirection(),
                "Gusts: " + getGusts(),
                "Air Pressure: " + getAirPressure(),
                "Humidity: " + getHumidity(),
                "Visibility: " + getVisibility()
        );
    }

    /**
     * @return the name of the station
     */
    @Override
    public String toString() {
        return getStationName();
    }

}
