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

    private StartGameObserver startGameObserver;

    void setStartGameObserver(StartGameObserver startGameObserver) {
        this.startGameObserver = startGameObserver;
    }

    public void startGame(Board board) {
        if (startGameObserver != null) {
            startGameObserver.startGame(board);
        }
    }

    @Override
    public void terminateGame() {
        gameTerminated = true;
    }

    boolean isGameTerminated() {
        return gameTerminated;
    }
}
