package de.iteratec.minesweeper.ngui;

import de.iteratec.minesweeper.Game;
import de.iteratec.minesweeper.MoveObserver;
import de.iteratec.minesweeper.api.Board;
import de.iteratec.minesweeper.api.Player;
import de.iteratec.minesweeper.util.Config;

/**
 * @author Patrick Hock
 */
public class NBoardGUIController implements Game.GameObserver, MoveObserver {

    private final NBoardGUI boardGUI;
    private NBoardGUIUpdater nBoardGUIUpdater;
    private Player player;
    private boolean delayMove;

    private NBoardGUIController(NBoardGUI boardGUI) {
        this.boardGUI = boardGUI;
    }

    public static NBoardGUIController create(NBoardGUI boardGUI) {
        return new NBoardGUIController(boardGUI);
    }

    private NFieldGUI.FieldClickListener fieldClickListener;

    @Override
    public void onGameStarted(Game game) {
        nBoardGUIUpdater = new NBoardGUIUpdater();
        nBoardGUIUpdater.setBoardGUI(boardGUI);
        if (fieldClickListener != null) {
            boardGUI.addFieldClickListener(fieldClickListener);
        }
        if (delayMove) {
            nBoardGUIUpdater.setDurationWaitAfterMoveInMillis(Config.getDurationWaitAfterMoveInMillis());
        }
        nBoardGUIUpdater.onGameStarted(game);
    }

    @Override
    public void onGameFinished(Game game) {
        nBoardGUIUpdater.onGameFinished(game);
    }

    @Override
    public void observeMove(int numberOfMoves, Board board, int x, int y) {
        nBoardGUIUpdater.observeMove(numberOfMoves, board, x, y);
    }

    void setDelayAtMove(boolean delayMove) {
        this.delayMove = delayMove;
    }

    void setFieldClickListener(NFieldGUI.FieldClickListener fieldClickListener) {
        this.fieldClickListener = fieldClickListener;
    }
}
