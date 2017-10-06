package de.iteratec.minesweeper.gui;

import de.iteratec.minesweeper.Game;
import de.iteratec.minesweeper.MoveObserver;
import de.iteratec.minesweeper.api.Board;
import de.iteratec.minesweeper.board.BoardFactory;
import de.iteratec.minesweeper.board.RandomBoard;
import javafx.application.Platform;

/**
 * Controller for the board gui of the current game.
 * For each new game a new controller will be created.
 *
 * @author Patrick Hock
 */
public abstract class BoardGUIController implements GameMoveListener, MoveObserver, StartGameListener {

    /**
     * Current game to be played.
     */
    private final Game game;

    /**
     * The GUI of the minesweeper board.
     */
    private final BoardGUI boardGUI;

    /**
     * The current player.
     */
    private final GUIPlayer player;

    /**
     * Indicates wether game is lost
     */
    private boolean gameIsLost;

    /**
     * The current board.
     */
    private RandomBoard board = null;

    /**
     * Observes game moves
     */
    private GameMoveListener moveListener;

    /**
     * Counter for remaining non-bomb fields which haven't been revealed yet. If this counter is 0 the game is one.
     */
    private int remainingFieldsCounter = 1;

    BoardGUIController(BoardGUI boardGUI, GUIPlayer player, BoardFactory boardFactory) {
        this.boardGUI = boardGUI;
        this.player = player;
        this.game = new Game();
        this.game.setBoardFactory(boardFactory);
        this.game.setMoveObserver(this);
        this.player.setStartGameListener(this);
    }

    /**
     * Starts a new game in a new thread.
     */
    void newGame() {
        new Thread(() -> game.play(player)).start();
    }

    /**
     * Observes a move done by the game
     *
     * @param numberOfMoves the number of moves
     * @param board         the board
     * @param x             the x coordinate of the move
     * @param y             the y coordinate of the move
     */
    @Override
    public void observeMove(int numberOfMoves, Board board, int x, int y) {
        Board.Sense clickedSense = getBoard().get(x, y);
        updateRemainingFieldsCounter();
        updateBoardGui();
        final boolean clickedOnBomb = Board.Sense.UNKNOWN.equals(clickedSense);
        if (clickedOnBomb) {
            this.gameIsLost = true;
            setAllFields();
            revealOpenedFields(new RevealBombBehaviour());
            terminateGame();
        } else if (hasWon()) {
            terminateGame();
        }
        afterMove();
    }

    private void updateRemainingFieldsCounter() {
        this.remainingFieldsCounter = countRemainingFields();
    }


    @Override
    public void startGame(Board board) {
        setBoard((RandomBoard) board);
        updateRemainingFieldsCounter();
        Platform.runLater(() -> {
            final int width = getBoard().getWidth();
            final int height = getBoard().getHeight();
            getBoardGUI().loadNewBoard(width, height);
        });
        afterMove();
    }

    @Override
    public void afterMove() {
        if (moveListener != null) {
            moveListener.afterMove();
        }
    }

    private int countRemainingFields() {
        int counter = board.getWidth() * board.getHeight();
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if (!board.get(x, y).equals(Board.Sense.UNKNOWN)) {
                    counter--;
                }
            }
        }
        return counter - board.getBombs();
    }

    private void updateBoardGui() {
        revealOpenedFields(new RevealTipBehaviour());
    }

    /**
     * Calls set() on each field of the {@link #board}
     */
    private void setAllFields() {
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                board.set(x, y);
            }
        }
    }

    private void revealOpenedFields(RevealBehaviour revealBehaviour) {
        Platform.runLater(() -> {
            for (int x = 0; x < board.getWidth(); x++) {
                for (int y = 0; y < board.getHeight(); y++) {
                    revealBehaviour.reveal(x, y);
                }
            }
        });
    }

    /**
     * @return true if the game was lost, else false
     */
    boolean hasLost() {
        return gameIsLost;
    }

    void setMoveListener(GameMoveListener moveListener) {
        this.moveListener = moveListener;
    }

    /**
     * @return true if the game was one, else false
     */
    boolean hasWon() {
        return getRemainingFieldsCounter() <= 0;
    }

    int getRemainingFieldsCounter() {
        return remainingFieldsCounter;
    }

    public RandomBoard getBoard() {
        return board;
    }

    public void setBoard(RandomBoard board) {
        this.board = board;
    }

    BoardGUI getBoardGUI() {
        return boardGUI;
    }

    public GUIPlayer getPlayer() {
        return player;
    }

    void terminateGame() {
        getPlayer().terminateGame();
        getBoardGUI().disableBoard();
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
    private class RevealBombBehaviour implements RevealBehaviour {
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
    private class RevealTipBehaviour implements RevealBehaviour {

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
