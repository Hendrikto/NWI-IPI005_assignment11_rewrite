package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.w3c.dom.Element;

/**
 * Hold information about the weather at a station.
 *
 * @author Hendrik Werner
 */
public class WeatherInfo {

    private static final String STATION_NAME_TAG = "stationnaam";
    private static final String DATE_TAG = "datum";
    private static final String HUMIDITY_TAG = "luchtvochtigheid";
    private static final String TEMP_TAG = "temperatuurGC";
    private static final String WIND_SPEED_TAG = "windsnelheidMS";
    private static final String WIND_DIRECTION_TAG = "windrichting";
    private static final String GUSTS_TAG = "windstotenMS";
    private static final String AIR_PRESSURE_TAG = "luchtdruk";
    private static final String VISIBILITY_TAG = "zichtmeters";
    private static final String RAIN_TAG = "regenMMPU";
    private static final String ICON_URL_TAG = "icoonactueel";
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
        date = dateFormat.parse(getContent(station, DATE_TAG));
        stationName = getContent(station, STATION_NAME_TAG);
        humidity = getContent(station, HUMIDITY_TAG);
        temp = getContent(station, TEMP_TAG);
        windSpeed = getContent(station, WIND_SPEED_TAG);
        gusts = getContent(station, GUSTS_TAG);
        airPressure = getContent(station, AIR_PRESSURE_TAG);
        windDirection = getContent(station, WIND_DIRECTION_TAG);
        visibility = getContent(station, VISIBILITY_TAG);
        rain = getContent(station, RAIN_TAG);
        iconURL = getContent(station, ICON_URL_TAG);
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
