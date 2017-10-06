package de.iteratec.minesweeper.gui;

import de.iteratec.minesweeper.api.Board;
import de.iteratec.minesweeper.board.BoardFactory;

/**
 * BoardGUIController for a game played by a human.
 *
 * @author Patrick Hock
 */
public class HumanGameBoardGUIController extends BoardGUIController implements FieldGUI.FieldClickListener {

    HumanGameBoardGUIController(BoardGUI boardGUI, BoardFactory boardFactory) {
        super(boardGUI, new HumanGamePlayer(), boardFactory);
    }

    @Override
    public void startGame(Board board) {
        super.startGame(board);
        getBoardGUI().addFieldClickListener(this);
    }

    @Override
    public HumanGamePlayer getPlayer() {
        return (HumanGamePlayer) super.getPlayer();
    }

    @Override
    public void onLeftClick(int x, int y) {
        getPlayer().setInput(x,y);
    }

    /**
     * Player for a human played game (human player is clicking the GUI).
     * Move is done by looping while waiting for input.
     */
    private static class HumanGamePlayer extends GUIPlayer {

        private int[] input = null;

        @Override
        public int[] move(Board board) {
            while (!inputGiven()) {
                if (isGameTerminated()) {
                    return null;
                }
                waitForInput();
            }
            final int[] result = input;
            input = null;
            return result;
        }

        private boolean inputGiven() {
            return input != null;
        }

        private void waitForInput() {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        void setInput(int x, int y) {
            this.input = new int[] {x, y};
        }
    }
}
