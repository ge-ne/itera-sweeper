package de.iteratec.minesweeper.gui;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import de.iteratec.minesweeper.api.Player;
import de.iteratec.minesweeper.board.BoardFactory;
import de.iteratec.minesweeper.board.RandomBoard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Main Controller for all GUI elements.
 *
 * @author Patrick Hock
 */
public class MainWindowGUIController implements Initializable, GameMoveListener, Main.ApplicationStoppedListener {

    @FXML
    private GridPane mainGrid;

    @FXML
    private ControlsGUI controlsGUI;

    @FXML
    private BoardSettingsGUI boardSettingsGUI;

    @FXML
    private StatsGUI statsGUI;

    @FXML
    private BoardGUI boardGUI;

    /**
     * The controller for the board gui
     */
    private BoardGUIController boardGUIController;

    /**
     * The factory which creates the board
     */
    private BoardFactory boardFactory;

    /**
     * All nonHumanPlayers that can be chosen to play the game.
     */
    private Map<String, Player> nonHumanPlayers = new HashMap<>();

    /**
     * The selected player at the time when the game was started.
     */
    private String selectedPlayer;

    public MainWindowGUIController() {
        Main.getInstance().registerApplicationStopListener(this);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.boardFactory = createBoardFactory();
        List<Player> simulationPlayers = findPlayers();
        for (Player player : simulationPlayers) {
            String playerName = player.getClass().getSimpleName();
            nonHumanPlayers.put(playerName, player);
        }
        controlsGUI.registerNonHuman(nonHumanPlayers);
        controlsGUI.addStartNewGameClickListener(this::startNewGame);
    }

    private BoardFactory createBoardFactory() {
        return () -> new RandomBoard(getBoardWidth(), getBoardHeight(), getBombsInBoard());
    }

    /**
     * Starts a new game depending on which player was selected
     * @param selectedPlayer The player selected from the dropdown list
     */
    private void startNewGame(String selectedPlayer) {
        stopRunningGame();
        this.selectedPlayer = selectedPlayer;
        boardGUIController = createGameGUIController(selectedPlayer);
        boardGUIController.setMoveListener(this);
        boardGUIController.newGame();
        Main.getInstance().getStage().setWidth(getBoardWidth() * 31 + 10);
        mainGrid.setPadding(new Insets(20, 10, 20, 20));
        Main.getInstance().getStage().setHeight(getBoardHeight() * 31 + 250);
    }

    private BoardGUIController createGameGUIController(String selectedItem) {
        if (selectedItem.equals(ControlsGUI.PLAYER_NAME_HUMAN)) {
            return new HumanGameBoardGUIController(boardGUI, boardFactory);
        }
        final Player player = nonHumanPlayers.get(selectedItem);
        return new SimulationGameBoardGUIController(boardGUI, boardFactory, player);
    }

    private void stopRunningGame() {
        if (boardGUIController != null) {
            boardGUIController.terminateGame();
        }
    }

    @Override
    public void afterMove() {
        if (boardGUIController.hasLost()) {
            statsGUI.onGameLost();
            updateText("Yout lost!");
            startNewGame(selectedPlayer, 3000);
            return;
        }
        if (boardGUIController.hasWon()) {
            statsGUI.onGameWon();
            updateText("You won!");
            startNewGame(selectedPlayer, 3000);
            return;
        }
        updateText("Fields: " + boardGUIController.getRemainingFieldsCounter());
    }

    private void startNewGame(String selectedPlayer, int delayInMillis) {
        if (selectedPlayer.equals(ControlsGUI.PLAYER_NAME_HUMAN)) {
            return;
        }
        if (!controlsGUI.isLoopGame()) {
            return;
        }
        sleep(delayInMillis);
        startNewGame(selectedPlayer);
    }

    private void sleep(int delayInMillis) {
        try {
            Thread.sleep(delayInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateText(String value) {
        controlsGUI.updateText(value);

    }

    @Override
    public void onApplicationStopped() {
        stopRunningGame();
    }

    /**
     * Loads all Player-Objects from package de.iteratec.minesweeper.nonHumanPlayers
     *
     * @return List of loaded nonHumanPlayers
     */
    private List<Player> findPlayers() {
        final List<Player> players = new ArrayList<>();
        try {
            final String packageName = "de.iteratec.minesweeper.players";
            final ImmutableSet<ClassPath.ClassInfo> topLevelClasses = ClassPath.from(getClass().getClassLoader()).getTopLevelClasses(packageName);
            for (ClassPath.ClassInfo topLevelClass : topLevelClasses) {
                final Class<?> loadedClass = topLevelClass.load();
                try {
                    Object newInstance = loadedClass.newInstance();

                    if (newInstance instanceof Player) {
                        Player player = (Player) newInstance;
                        players.add(player);
                    }
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }

    private int getBoardWidth() {
        return boardSettingsGUI.getBoardWidth();
    }

    private int getBoardHeight() {
        return boardSettingsGUI.getBoardHeight();
    }

    private int getBombsInBoard() {
        return boardSettingsGUI.getNumberOfBombs();
    }
}
