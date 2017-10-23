package de.iteratec.minesweeper.ngui;

import de.iteratec.minesweeper.Game;
import de.iteratec.minesweeper.MoveObserver;
import de.iteratec.minesweeper.api.Board;
import de.iteratec.minesweeper.util.Config;
import javafx.application.Platform;

/**
 * @author Patrick Hock
 *
 */
public class NBoardGUIUpdater implements Game.GameObserver, MoveObserver {

    private NBoardGUI boardGUI;
    private int durationWaitAfterMoveInMillis;

    void setBoardGUI(NBoardGUI boardGUI) {
        this.boardGUI = boardGUI;
    }

    void setDurationWaitAfterMoveInMillis(int durationWaitAfterMoveInMillis) {
        this.durationWaitAfterMoveInMillis = durationWaitAfterMoveInMillis;
    }

    private void revealOpenedFields(Board board) {
        new NFieldRevealer().revealOpenedFields(board, boardGUI);
    }

    @Override
    public void onGameStarted(Game game) {
        Platform.runLater(() -> boardGUI.newGame(game.getBoard().getWidth(), game.getBoard().getHeight()));
    }

    @Override
    public void onGameFinished(Game game) {
        wait(Config.getDurationWaitAfterGameInMillis());
    }

    @Override
    public void observeMove(int numberOfMoves, Board board, int x, int y) {
        Board.Sense sense = board.get(x, y);
        Platform.runLater(() -> boardGUI.animateClick(x, y));
        Platform.runLater(() -> revealOpenedFields(board));
        if (sense.equals(Board.Sense.UNKNOWN)) {
            revealBomb(x, y);
        }
        wait(durationWaitAfterMoveInMillis);
    }

    private void revealBomb(int x, int y) {
        Platform.runLater(() -> boardGUI.revealBomb(x, y));
    }

    private void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
