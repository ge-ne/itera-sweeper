package de.iteratec.minesweeper.gui;

import de.iteratec.minesweeper.GameSeries;
import de.iteratec.minesweeper.api.Board;
import de.iteratec.minesweeper.api.Player;
import javafx.application.Platform;

/**
 * @author Patrick Hock
 */
public class GameSeriesBoardGUIController extends AbstractBoardGUIController implements NewGameInSeriesListener {

    GameSeriesPlayer gameSeriesPlayer;

    private BoardGUI boardGUI;

    void start(Player player) {
        this.gameSeriesPlayer = new GameSeriesPlayer(player);
        // TODO 10 nicht fest
        GameSeries gameSeries = new GameSeries(gameSeriesPlayer).run(10);

    }

    void onNewGame() {
        Platform.runLater(() -> {
            final int width = getBoard().getWidth();
            final int height = getBoard().getHeight();
            getBoardGUI().loadNewBoard(width, height);
        });
    }

    protected BoardGUI getBoardGUI() {
        return this.boardGUI;
    }

    public void setBoardGUI(BoardGUI boardGUI) {
        this.boardGUI = boardGUI;
    }

    protected Board getBoard() {
        return gameSeriesPlayer.getBoard();
    }

    private class GameSeriesPlayer implements Player {

        private final Player actualPlayer;

        private Board board;

        private GameSeriesPlayer(Player actualPlayer) {
            this.actualPlayer = actualPlayer;
        }

        public Board getBoard() {
            return board;
        }

        @Override
        public void startGame(Board board) {
            this.board = board;
            // TODO observer
            GameSeriesBoardGUIController.this.onNewGame();
        }

        @Override
        public void terminateGame(boolean won) {

        }

        @Override
        public int[] move(Board board) {

            int[] moveResult = actualPlayer.move(board);

            return moveResult;
        }
    }
}
