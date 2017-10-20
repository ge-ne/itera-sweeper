package de.iteratec.minesweeper.gui;

import de.iteratec.minesweeper.api.Board;
import javafx.application.Platform;

/**
 * @author Patrick Hock
 *
 */
// TODO Vererbungshierarchie umbauen
public abstract class AbstractBoardGUIController {

    protected abstract Board getBoard();

    protected abstract BoardGUI getBoardGUI();

    /**
     * Reveals all fields that have been opened so far (concerning the given behaviour)
     * @param revealBehaviour The behaviour how the fields should be revealed.
     */
    protected void revealOpenedFields(RevealBehaviour revealBehaviour) {
        Platform.runLater(() -> {
            for (int x = 0; x < getBoard().getWidth(); x++) {
                for (int y = 0; y < getBoard().getHeight(); y++) {
                    revealBehaviour.reveal(x, y);
                }
            }
        });
    }

    /**
     * Behaviour which indicates what happens when a field is revealed.
     */
    private interface RevealBehaviour {
        void reveal(int x, int y);
    }

    /**
     * A bomb should be revealed.
     */
    protected class RevealBombBehaviour implements RevealBehaviour {
        @Override
        public void reveal(int x, int y) {
            final Board.Sense sense = getBoard().get(x, y);
            final boolean isBomb = Board.Sense.UNKNOWN.equals(sense);
            if (isBomb) {
                getBoardGUI().revealBomb(x, y);
            }
        }
    }



    /**
     * A tip (field containing a number of neighbouring fields) should be revealed.
     */
    protected class RevealTipBehaviour implements RevealBehaviour {

        @Override
        public void reveal(int x, int y) {
            final Board.Sense sense = getBoard().get(x, y);
            final boolean isATip = !Board.Sense.UNKNOWN.equals(sense);
            if (isATip) {
                getBoardGUI().revealTip(x, y, sense);
            }
        }
    }
}
