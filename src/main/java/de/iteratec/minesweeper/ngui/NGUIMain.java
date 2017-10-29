
package de.iteratec.minesweeper.ngui;

import java.net.URL;
import java.util.ResourceBundle;

import de.iteratec.minesweeper.Game;
import de.iteratec.minesweeper.api.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * @author Patrick Hock
 */
public class NGUIMain
        implements
            Initializable,
            NNewGameSeriesListener,
            NPlayButtonController.PlayButtonListener {

    @FXML
    public ImageView logoImageView;

    private NLogoImageViewController logoImageViewController;

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

    private NPlayerController playerController;

    public NGUIMain() {
        Main.getInstance().registerApplicationStopListener(
            () -> gameSeriesController.stopGameSeries());
    }

    @FXML
    public void startNewGameSeries() {

        final int seed = Integer.parseInt(this.seedTextField.getText());
        final int runs = this.runsTextFieldController.getRuns();
        final Player player =
                this.playersComboBoxController.getSelectedPlayer();

        playerController.setPlayer(player);

        boardGUIController.setDelayAtMove(!playerController.isHumanPlayer());
        boardGUIController.setFieldClickListener(playerController);

        statsLabelController.setPlayerName(player.getClass().getSimpleName());

        gameSeriesController.setPlayer(player);
        gameSeriesController.setSeed(seed);
        gameSeriesController.setRuns(runs);
        gameSeriesController.startNewGameSeries();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        playersComboBoxController =
                NPlayersComboBoxController.create(playersComboBox);
        statsLabelController = NStatsLabelController.create(statsLabel);
        boardGUIController = NBoardGUIController.create(boardGUI);
        mainWindowController = NMainWindowController.create();
        runsTextFieldController =
                NRunsTextFieldController.create(runsTextField);
        NBoardDimensionTextFieldsController.create(boardDimensionXTextField,
            boardDimensionYTextField);
        playButtonController = NPlayButtonController.create(playButton);
        playButtonController.setButtonListener(this);
        gameSeriesController = NGameSeriesController.create();
        gameSeriesController.setGameSeriesListener(this);
        playerController = NPlayerController.create();
        logoImageViewController =
                NLogoImageViewController.create(logoImageView);
    }

    @Override
    public void onNewGameInSeries(Game game) {

        game.addGameObserver(mainWindowController);
        game.addGameObserver(logoImageViewController);
        game.addGameObserver(statsLabelController);
        game.addGameObserver(boardGUIController);
        game.setMoveObserver(boardGUIController);
        game.addGameObserver(playButtonController);
    }

    @Override
    public void onGameSeriesFinished() {

        this.playButtonController.setEnabled(true);
        this.playButtonController.setTextStop();
    }

    @Override
    public void onPlayButtonClicked() {

        if (gameSeriesController.isGameSeriesRunning()) {
            this.playButtonController.setEnabled(false);
            this.gameSeriesController.stopGameSeries();
            this.playerController.onGameFinished();
        } else {
            this.startNewGameSeries();
            this.playButtonController.setTextPlay();
        }
    }
}
