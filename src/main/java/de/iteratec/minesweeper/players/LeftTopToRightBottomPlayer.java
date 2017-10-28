
package de.iteratec.minesweeper.players;

import static de.iteratec.minesweeper.board.BoardUtils.isFree;

import de.iteratec.minesweeper.api.Board;
import de.iteratec.minesweeper.api.Move;
import de.iteratec.minesweeper.api.Player;

/**
 * @author Patrick Hock
 * @created 22.10.2017
 */
@SuppressWarnings("unused")
public class LeftTopToRightBottomPlayer implements Player {

    @Override
    public Move move(Board board) {

        int[] click = new int[]{0, 0};
        while (!isFree(board, click)) {
            int x = click[0] + 1;
            int y = click[1];
            if (x >= board.getWidth()) {
                y++;
                x = 0;
            }
            click = new int[]{x, y};
        }
        return new Move(click[0], click[1]);
    }

}
