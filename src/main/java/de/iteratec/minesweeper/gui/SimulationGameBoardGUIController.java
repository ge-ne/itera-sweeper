package de.iteratec.minesweeper.gui;

import de.iteratec.minesweeper.api.Board;
import de.iteratec.minesweeper.api.Player;
import de.iteratec.minesweeper.board.BoardFactory;
import javafx.application.Platform;

/**
 * Game controller for a simulation of a game.
 *
 * @author Patrick Hock
 */
public class SimulationGameBoardGUIController extends BoardGUIController {

    private final static int DURATION_MOVE_DELAY_IN_MILLIS = 1000;
    private final static int DURATION_CLICK_ANIMATION_IN_MILLIS = 500;

    SimulationGameBoardGUIController(BoardGUI boardGUI, BoardFactory boardFactory, Player player) {
        super(boardGUI, new SimulationGamePlayer(player), boardFactory);
    }

    @Override
    public void observeMove(int numberOfMoves, Board board, int x, int y) {
        threadSleep(DURATION_MOVE_DELAY_IN_MILLIS / 2);
        animateClick(x,y);
        super.observeMove(numberOfMoves, board, x, y);
        threadSleep(DURATION_MOVE_DELAY_IN_MILLIS / 2);
    }

    private void animateClick(int x, int y) {
        Platform.runLater(() -> getBoardGUI().animateClick(x, y, DURATION_CLICK_ANIMATION_IN_MILLIS));
    }

    private void threadSleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Player for a simulated game. Delegates move() to the given gameSeriesPlayer (strategy).
     */
    private static class SimulationGamePlayer extends GUIPlayer {

        /** Player to which move-Method is delegated to. **/
        private final Player strategy;

        /**
         * Creates new SimulationGamePlayer
         * @param strategy The gameSeriesPlayer to which move-Method is delegated.
         */
        SimulationGamePlayer(Player strategy) {
            this.strategy = strategy;
        }

        /**
         * Delegates move to the strategy gameSeriesPlayer.
         *
         * {@inheritDoc}
         */
        @Override
        public int[] move(Board board) {
            if (isGameTerminated()) {
                return null;
            }
            return strategy.move(board);
        }
    }
}
