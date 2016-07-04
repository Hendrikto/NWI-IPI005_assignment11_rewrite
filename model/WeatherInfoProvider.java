package model;

import javafx.beans.property.ListProperty;

/**
 * Provide a list property that holds weather info.
 *
 * @author Hendrik Werner
 */
public interface WeatherInfoProvider {

    public ListProperty<WeatherInfo> weatherInfoProperty();

    public void refresh();

}
