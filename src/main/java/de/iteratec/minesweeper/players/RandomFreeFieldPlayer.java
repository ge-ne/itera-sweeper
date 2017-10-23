package de.iteratec.minesweeper.players;

import de.iteratec.minesweeper.api.Board;
import de.iteratec.minesweeper.api.Player;

import java.util.Random;

import static de.iteratec.minesweeper.board.BoardUtils.isFree;

/**
 * This player clicks randomly on any free field of the minesweeper board.
 *
 * @author Patrick Hock
 */
@SuppressWarnings("unused")
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
}
