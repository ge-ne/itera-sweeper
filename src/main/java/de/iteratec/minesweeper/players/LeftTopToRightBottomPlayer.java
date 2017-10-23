package de.iteratec.minesweeper.players;

import de.iteratec.minesweeper.api.Board;
import de.iteratec.minesweeper.api.Player;

import static de.iteratec.minesweeper.board.BoardUtils.isFree;

/**
 * @author Patrick Hock
 * @created 22.10.2017
 */
@SuppressWarnings("unused")
public class LeftTopToRightBottomPlayer implements Player {
    @Override
    public int[] move(Board board) {
        int[] click = new int[] {0, 0};
        while(!isFree(board, click)) {
            int x = click[0] + 1;
            int y = click[1];
            if (x >= board.getWidth()) {
                y++;
                x = 0;
            }
            click = new int[]{x, y};
        }
        return click;
    }

}
