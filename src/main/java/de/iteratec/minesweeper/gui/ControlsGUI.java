package de.iteratec.minesweeper.gui;

import de.iteratec.minesweeper.api.Player;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * GUI for the basic game controls (play, select player, etc.)
 *
 * @author Patrick Hock
 */
public class ControlsGUI extends VBox implements Initializable {

    static final String PLAYER_NAME_HUMAN = "Human";

    @FXML
    private Label remainingFieldsLabel;

    @FXML
    private ComboBox<String> playersComboBox;

    @FXML
    private CheckBox loopCheckBox;

    private Map<String, Player> players = new HashMap<>();

    private StartNewGameClickListener startNewGameClickListener;

    public ControlsGUI() {
        final URL controlsFxmlFile = getClass().getClassLoader().getResource("controls.fxml");
        final FXMLLoader fxmlLoader = new FXMLLoader(controlsFxmlFile);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    protected void startNewGame() {
        if (this.startNewGameClickListener != null) {
            startNewGameClickListener.startNewGame(getSelectedPlayer());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // do nothing
    }

    void registerNonHuman(Map<String, Player> nonHumanPlayers) {
        players.put(PLAYER_NAME_HUMAN, null);
        players.putAll(nonHumanPlayers);
        playersComboBox.setItems(getPlayerStrategies());
        playersComboBox.getSelectionModel().selectFirst();
    }



    private ObservableList<String> getPlayerStrategies() {
        return FXCollections.observableArrayList(this.players.keySet());
    }

    private String getSelectedPlayer() {
        return playersComboBox.getSelectionModel().getSelectedItem();
    }

    boolean isLoopGame() {
        return loopCheckBox.isSelected();
    }

    void updateText(String value) {
        Platform.runLater(() -> remainingFieldsLabel.setText(value));
    }

    void addStartNewGameClickListener(StartNewGameClickListener listener) {
        this.startNewGameClickListener = listener;
    }

    @FunctionalInterface
    public interface StartNewGameClickListener {
        void startNewGame(String player);
    }
}