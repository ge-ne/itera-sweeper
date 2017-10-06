package de.iteratec.minesweeper.players;

import de.iteratec.minesweeper.api.Board;
import de.iteratec.minesweeper.api.Player;

import java.util.Random;

/**
 * This player clicks randomly on any free field of the minesweeper board.
 *
 * @author Patrick Hock
 */
public class RandomFreeFieldPlayer implements Player {

    private final Random random = new Random();

    @Override
    public int[] move(Board board) {
        int[] click;
        do {
            final int x = random.nextInt(board.getWidth());
            final int y = random.nextInt(board.getHeight());
            click = new int[]{x, y};
        } while (!isFree(board, click));
        return click;
    }

    /**
     * Checks if there is a free field on the given position of the board.
     * @param board The current board.
     * @param pos The position as two-dimensional array (must be within board dimensions).
     * @return True if the position is free. Otherwise false (if the field was revealed already).
     */
    private boolean isFree(Board board, int[] pos) {
        final Board.Sense boardSense = board.get(pos[0], pos[1]);
        return boardSense == Board.Sense.UNKNOWN;
    }
}
