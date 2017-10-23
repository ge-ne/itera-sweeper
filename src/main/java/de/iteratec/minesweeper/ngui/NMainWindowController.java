package de.iteratec.minesweeper.ngui;

import de.iteratec.minesweeper.Game;
import de.iteratec.minesweeper.api.Board;
import de.iteratec.minesweeper.util.Config;

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
        Main.getInstance().getStage().setWidth(Math.max(NConstants.MENU_WIDTH, width * Config.getFieldDimension() + 50));
        int height = board.getHeight();
        Main.getInstance().getStage().setHeight(height * Config.getFieldDimension() + NConstants.MENU_HEIGHT);
    }

    @Override
    public void onGameStarted(Game game) {
        adjustGridSize(game.getBoard());
    }

    @Override
    public void onGameFinished(Game game) {

    }
}
