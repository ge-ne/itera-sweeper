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
 * @author Patrick Hock
 */
public class StatsGUI extends HBox implements Initializable {

    @FXML
    private Label statsLabel;

    private int gamesCount = 0;
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
        gamesCount++;
        gamesWonCount++;
        updateGui();
    }

    void onGameLost() {
        gamesCount++;
        updateGui();
    }


    private void updateGui() {
        String won = "" + gamesWonCount;
        String games = "" + gamesCount;
        Platform.runLater(() -> statsLabel.setText("won " + won + " of " + games + " games"));
    }
}
