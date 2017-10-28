
package de.iteratec.minesweeper.players;

import de.iteratec.minesweeper.api.Board;
import de.iteratec.minesweeper.api.Player;
import de.iteratec.minesweeper.ngui.NFieldGUI;

/**
 * @author Patrick Hock
 * @created 23.10.2017
 */
public class HumanPlayer implements Player, NFieldGUI.FieldClickListener {

    private Move lastMove = null;

    private boolean terminated;

    private final Object lock = new Object();

    @Override
    public void startGame(Board board) {

        synchronized (lock) {
            lastMove = null;
        }
        setTerminated(false);
    }

    @Override
    public void terminateGame() {

        setTerminated(true);
    }

    @Override
    public de.iteratec.minesweeper.api.Move move(Board board) {

        while (!isTerminated()) {
            if (lastMove != null) {
                synchronized (lock) {
                    de.iteratec.minesweeper.api.Move result =
                            new de.iteratec.minesweeper.api.Move(lastMove.x,
                                lastMove.y);
                    lastMove = null;
                    return result;
                }
            }
            sleep(100);
        }
        return new de.iteratec.minesweeper.api.Move(-1, -1);
    }

    private void sleep(int millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLeftClick(int x, int y) {

        synchronized (lock) {
            this.lastMove = new Move(x, y);
        }
    }

    public boolean isTerminated() {

        return terminated;
    }

    public void setTerminated(boolean terminated) {

        this.terminated = terminated;
    }

    private class Move {

        final int x, y;

        private Move(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
