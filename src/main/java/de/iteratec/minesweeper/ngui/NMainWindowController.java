package de.iteratec.minesweeper.ngui;

import de.iteratec.minesweeper.Game;
import de.iteratec.minesweeper.api.Board;

/**
 * @author Patrick Hock
 */
public class NMainWindowController implements Game.GameObserver {


    private NMainWindowController() {
    }

    public static NMainWindowController create() {
        return new NMainWindowController();
    }

    private void adjustGridSize(Board board) {
        int width = board.getWidth();
        Main.getInstance().getStage().setWidth(Math.max(NConstants.MENU_WIDTH, width * NConstants.FIELD_DIMENSION + 50));
        int height = board.getHeight();
        Main.getInstance().getStage().setHeight(height * NConstants.FIELD_DIMENSION + NConstants.MENU_HEIGHT);
    }

    @Override
    public void onGameStarted(Game game) {
        adjustGridSize(game.getBoard());
    }

    @Override
    public void onGameFinished(Game game) {

    }
}
