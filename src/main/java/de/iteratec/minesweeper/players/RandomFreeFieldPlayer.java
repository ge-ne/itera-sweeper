package de.iteratec.minesweeper.players;

import de.iteratec.minesweeper.api.Board;
import de.iteratec.minesweeper.api.Player;

import java.util.Random;

/**
 * @author Patrick Hock
 */
public class RandomFreeFieldPlayer implements Player {

    private final Random random = new Random();

    @Override
    public int[] move(Board board) {
        int[] result;
        do {
            result = new int[]{random.nextInt(board.getWidth()),
                    random.nextInt(board.getHeight())};
        } while (!isFree(board, result));
        return result;
    }

    private boolean isFree(Board board, int[] result) {
        return board.get(result[0], result[1]) == Board.Sense.UNKNOWN;
    }
}
