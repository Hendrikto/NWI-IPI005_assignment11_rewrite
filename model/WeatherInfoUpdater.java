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
        provider.refresh();
        return null;
    }

    @Override
    protected void scheduled() {
        selected = selection.getSelectedIndex();
    }

    @Override
    protected void running() {
    }

    @Override
    protected void succeeded() {
        provider.commit();
        selection.select(selected);
    }

}
