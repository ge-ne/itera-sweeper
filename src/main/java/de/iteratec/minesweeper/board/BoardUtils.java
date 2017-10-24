package de.iteratec.minesweeper.board;

import de.iteratec.minesweeper.api.Board;

/**
 * @author Patrick Hock
 */
public class BoardUtils {

    /**
     * Checks if there is a free field on the given position of the board.
     * @param board The current board.
     * @param pos The position as two-dimensional array (must be within board dimensions).
     * @return True if the position is free. Otherwise false (if the field was revealed already).
     */
    public static boolean isFree(Board board, int[] pos) {
        final Board.Sense boardSense = board.get(pos[0], pos[1]);
        return boardSense == Board.Sense.UNKNOWN;
    }
}
