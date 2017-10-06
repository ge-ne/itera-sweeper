package de.iteratec.minesweeper.gui;

import de.iteratec.minesweeper.api.Board;

/**
 * Observer for the even startGame.
 *
 * @author Patrick Hock
 */
public interface StartGameListener {

    void startGame(Board board);
}
