package de.iteratec.minesweeper.gui;

/**
 * Listener for game move events
 *
 * @author Patrick Hock
 */
interface GameMoveListener {

    /**
     * Event is triggered after each move.
     */
    void afterMove();
}
