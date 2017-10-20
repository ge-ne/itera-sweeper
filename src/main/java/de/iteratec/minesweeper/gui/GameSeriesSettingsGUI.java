package de.iteratec.minesweeper.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Gui for choosing a game series to be played.
 *
 * @author Patrick Hock
 */
public class GameSeriesSettingsGUI extends HBox implements Initializable {

    @FXML
    private TextField seedTextField;

    @FXML
    private Button playSeriesButton;

    private StartNewGameSeriesListener startNewGameSeriesListener;

    public GameSeriesSettingsGUI() {
        final URL fxmlFile = getClass().getClassLoader().getResource("game_series_settings.fxml");
        final FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void startNewGameSeries() {
        this.startNewGameSeriesListener.newGameSeries(Integer.parseInt(this.seedTextField.getText()));
    }

    public void registerListener(StartNewGameSeriesListener listener) {
        this.startNewGameSeriesListener = listener;
    }
}
