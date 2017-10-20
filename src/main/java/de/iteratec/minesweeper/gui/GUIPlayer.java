package de.iteratec.minesweeper.gui;

import de.iteratec.minesweeper.api.Board;
import de.iteratec.minesweeper.api.Player;

/**
 * Player playing on a GUI.
 *
 * @author Patrick Hock
 */
public abstract class GUIPlayer implements Player {

    private boolean gameTerminated = false;

    private StartGameListener startGameListener;

    void setStartGameListener(StartGameListener startGameListener) {
        this.startGameListener = startGameListener;
    }

    public void startGame(Board board) {
        if (startGameListener != null) {
            startGameListener.startGame(board);
        }
    }

    @Override
    public void terminateGame(boolean won) {
        gameTerminated = true;
    }

    boolean isGameTerminated() {
        return gameTerminated;
    }
}
