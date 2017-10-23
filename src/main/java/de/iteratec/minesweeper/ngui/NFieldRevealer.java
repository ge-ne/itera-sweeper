package de.iteratec.minesweeper.ngui;

import de.iteratec.minesweeper.api.Board;

/**
 * @author Patrick Hock
 */
class NFieldRevealer {

    /**
     * Reveals all fields that have been opened so far (concerning the given behaviour)
     */
    void revealOpenedFields(Board board, NBoardGUI boardGUI) {
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                reveal(board, boardGUI, x, y);
            }
        }
    }


    private void reveal(Board board, NBoardGUI boardGUI, int x, int y) {
        final Board.Sense sense = board.get(x, y);
        final boolean isATip = !Board.Sense.UNKNOWN.equals(sense);
        if (isATip) {
            boardGUI.revealTip(x, y, sense);
        }
        if (Board.Sense.BOMB.equals(sense)) {
            boardGUI.revealBomb(x, y);
        }
    }

}
