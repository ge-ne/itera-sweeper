package de.iteratec.minesweeper.gui;

import de.iteratec.minesweeper.api.Board;

/**
 * Observer for startGame event.
 *
 * @author Patrick Hock
 */
public interface StartGameObserver {
    void startGame(Board board);
}
