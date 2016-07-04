package model;

import javafx.concurrent.Task;
import javafx.scene.control.SingleSelectionModel;

/**
 * @author Hendrik Werner
 */
public class WeatherInfoUpdater extends Task<Void> {

    private final WeatherInfoProvider provider;
    private final SingleSelectionModel selection;
    private int selected;

    public WeatherInfoUpdater(WeatherInfoProvider provider, SingleSelectionModel selection) {
        this.provider = provider;
        this.selection = selection;
    }

    @Override
    protected Void call() throws Exception {
        return null;
    }

    @Override
    protected void scheduled() {
        selected = selection.getSelectedIndex();
    }

    @Override
    protected void running() {
        provider.refresh();
    }

    @Override
    protected void succeeded() {
        selection.select(selected);

    }

}
