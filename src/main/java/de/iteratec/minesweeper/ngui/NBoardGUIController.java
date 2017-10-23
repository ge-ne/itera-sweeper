package de.iteratec.minesweeper.ngui;

import de.iteratec.minesweeper.Game;
import de.iteratec.minesweeper.MoveObserver;
import de.iteratec.minesweeper.api.Board;
import de.iteratec.minesweeper.api.Player;
import de.iteratec.minesweeper.players.HumanPlayer;

/**
 * @author Patrick Hock
 */
public class NBoardGUIController implements Game.GameObserver, MoveObserver {

    private final NBoardGUI boardGUI;
    private NBoardGUIUpdater nBoardGUIUpdater;
    private Player player;

    private NBoardGUIController(NBoardGUI boardGUI) {
        this.boardGUI = boardGUI;
    }

    public static NBoardGUIController create(NBoardGUI boardGUI) {
        return new NBoardGUIController(boardGUI);
    }

    @Override
    public void onGameStarted(Game game) {
        nBoardGUIUpdater = new NBoardGUIUpdater();
        nBoardGUIUpdater.setBoardGUI(boardGUI);
        if (isHumanPlayer()) {
            boardGUI.addFieldClickListener((HumanPlayer) player);
        } else {
            nBoardGUIUpdater.setDurationWaitAfterMoveInMillis(NConstants.DURATION_WAIT_AFTER_MOVE_IN_MILLIS);
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

    private boolean isHumanPlayer() {
        return this.player != null && this.player.getClass().equals(HumanPlayer.class);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
