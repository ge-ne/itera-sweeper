
package de.iteratec.minesweeper.players;

import static de.iteratec.minesweeper.board.BoardUtils.isFree;

import java.util.Random;

import de.iteratec.minesweeper.api.Board;
import de.iteratec.minesweeper.api.Move;
import de.iteratec.minesweeper.api.Player;

/**
 * This player clicks randomly on any free field of the minesweeper board.
 *
 * @author Patrick Hock
 */
@SuppressWarnings("unused")
public class RandomFreeFieldPlayer implements Player {

    private final Random random = new Random();

    @Override
    public Move move(Board board) {

        int[] click;
        do {
            final int x = random.nextInt(board.getWidth());
            final int y = random.nextInt(board.getHeight());
            click = new int[]{x, y};
        } while (!isFree(board, click));
        return new Move(click[0], click[1]);
    }
}
