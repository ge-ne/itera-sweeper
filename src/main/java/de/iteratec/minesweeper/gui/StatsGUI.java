package de.iteratec.minesweeper.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Gui which shows the current statistics.
 *
 * @author Patrick Hock
 */
public class StatsGUI extends HBox implements Initializable {

    @FXML
    private Label statsLabel;

    private int gamesPlayedCount = 0;

    private int gamesWonCount = 0;

    public StatsGUI() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("stats.fxml"));
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

    void onGameWon() {
        gamesPlayedCount++;
        gamesWonCount++;
        updateGui();
    }

    void onGameLost() {
        gamesPlayedCount++;
        updateGui();
    }

    private void updateGui() {
        final String gamesWon = "" + gamesWonCount;
        final String gamesPlayed = "" + gamesPlayedCount;
        final String guiText = "won " + gamesWon + " of " + gamesPlayed + " games";
        Platform.runLater(() -> statsLabel.setText(guiText));
    }
}
