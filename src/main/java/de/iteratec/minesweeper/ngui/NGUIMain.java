package de.iteratec.minesweeper.ngui;

import de.iteratec.minesweeper.Game;
import de.iteratec.minesweeper.api.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Patrick Hock
 */
public class NGUIMain implements Initializable, NNewGameSeriesListener, NPlayButtonController.PlayButtonListener {

    @FXML
    private Button playButton;
    private NPlayButtonController playButtonController;

    @FXML
    private NBoardGUI boardGUI;
    private NBoardGUIController boardGUIController;

    @FXML
    private TextField seedTextField;

    @FXML
    private Label statsLabel;
    private NStatsLabelController statsLabelController;

    @FXML
    private ComboBox playersComboBox;
    private NPlayersComboBoxController playersComboBoxController;

    @FXML
    private TextField runsTextField;
    private NRunsTextFieldController runsTextFieldController;

    @FXML
    private TextField boardDimensionXTextField;

    @FXML
    private TextField boardDimensionYTextField;

    private NMainWindowController mainWindowController;

    private NGameSeriesController gameSeriesController;


    public NGUIMain() {
        Main.getInstance().registerApplicationStopListener(this::stopGameSeries);
    }

    @FXML
    public void startNewGameSeries() {

        final int seed = Integer.parseInt(this.seedTextField.getText());
        final Player selectedPlayer = this.playersComboBoxController.getSelectedPlayer();
        final int runs = this.runsTextFieldController.getRuns();

        boardGUIController.setPlayer(selectedPlayer);
        gameSeriesController.setPlayer(selectedPlayer);
        gameSeriesController.setSeed(seed);
        gameSeriesController.setRuns(runs);
        gameSeriesController.startNewGameSeries();
    }

    private void stopGameSeries() {
        gameSeriesController.stopGameSeries();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playersComboBoxController = NPlayersComboBoxController.create(playersComboBox);
        statsLabelController = NStatsLabelController.create(statsLabel);
        boardGUIController = NBoardGUIController.create(boardGUI);
        mainWindowController = NMainWindowController.create();
        runsTextFieldController = NRunsTextFieldController.create(runsTextField);
        NBoardDimensionTextFieldsController.create(boardDimensionXTextField, boardDimensionYTextField);
        playButtonController = NPlayButtonController.create(playButton);
        playButtonController.setButtonListener(this);
        gameSeriesController = NGameSeriesController.create();
        gameSeriesController.setGameSeriesListener(this);
    }

    @Override
    public void onNewGameInSeries(Game game) {
        game.addGameObserver(mainWindowController);
        game.addGameObserver(statsLabelController);
        game.addGameObserver(boardGUIController);
        game.setMoveObserver(boardGUIController);
    }

    @Override
    public void onGameSeriesFinished() {
        this.playButtonController.seriesHasFinishedOrStopped();
    }

    @Override
    public void onPlayButtonClicked() {
        if (gameSeriesController.isGameSeriesRunning()) {
            this.stopGameSeries();
            this.playButtonController.seriesHasFinishedOrStopped();
        } else {
            this.startNewGameSeries();
            this.playButtonController.seriesHasBegun();
        }
    }
}
