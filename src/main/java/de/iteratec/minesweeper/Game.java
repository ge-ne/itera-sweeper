/*
 * Copyright (C) 2017 Gerd Neugebauer
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation; either version 2.1 of the License, or (at your
 * option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 */

package de.iteratec.minesweeper;

import de.iteratec.minesweeper.api.Board;
import de.iteratec.minesweeper.api.Player;
import de.iteratec.minesweeper.board.BoardFactory;
import de.iteratec.minesweeper.board.ModifiableBoard;
import de.iteratec.minesweeper.board.RandomBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a driver for a game of Minesweeper. A new board is allocated
 * and filled. Then the player is started and run until the result has been
 * determined.
 *
 * @author <a href="mailto:Gerd.Neugebauer@iteratec.de">Gerd Neugebauer</a>
 */
public class Game {

    /**
     * The field <tt>board</tt> contains the board.
     */
    private ModifiableBoard board;

    /**
     * The field <tt>moves</tt> contains the number of moves.
     */
    private int moves = 0;

    /**
     * The field <tt>won</tt> contains the information whether the game been
     * won.
     */
    private boolean won = false;

    /**
     * The field <tt>moveObserver</tt> contains the optional MoveObserver.
     */
    private MoveObserver moveObserver = null;


    private List<GameObserver> gameObservers = new ArrayList<>();


    /**
     * The field <tt>boardFactory</tt> contains the factory to produce a new
     * board.
     */
    private BoardFactory boardFactory = RandomBoard::new;

    /**
     * Creates a new object.
     */
    public Game() {

    }

    /**
     * Getter for board.
     *
     * @return the board
     */
    public Board getBoard() {

        return board;
    }

    /**
     * Getter for moves.
     *
     * @return the moves
     */
    public int getMoves() {

        return moves;
    }

    /**
     * Getter for won.
     *
     * @return the won
     */
    public boolean isWon() {

        return won;
    }

    /**
     * Run the game until the result has been fixed.
     *
     * @param player the player
     *
     * @return <code>true</code> iff the game has been won
     */
    public Game play(Player player) {

        if (player == null) {
            throw new NullPointerException();
        }

        board = boardFactory.createBoard();

        player.startGame(board);
        notifyGameStarted();
        try {
            while (!board.isCompleted()) {
                int[] move = player.move(board);
                moves++;
                boolean lost = move == null || !board.set(move[0], move[1]);
                if (moveObserver != null && move != null) {
                    moveObserver.observeMove(moves, board, move[0], move[1]);
                }
                if (lost) {
                    won = false;
                    notifyGameFinished();
                    return this;
                }
            }

            won = true;
        } catch (Exception e) {
            won = false;
        } finally {
            player.terminateGame(won);
        }
        notifyGameFinished();
        return this;
    }

    /**
     * Setter for boardFactory.
     *
     * @param boardFactory the boardFactory to set
     */
    public Game setBoardFactory(BoardFactory boardFactory) {

        this.boardFactory = boardFactory;
        return this;
    }

    /**
     * Setter for move observer.
     *
     * @param moveObserver the move observer to set
     */
    public Game setMoveObserver(MoveObserver moveObserver) {
        this.moveObserver = moveObserver;
        return this;
    }

    public void addGameObserver(GameObserver gameObserver) {
        this.gameObservers.add(gameObserver);
    }

    private void notifyGameFinished() {
        for (GameObserver gameObserver : gameObservers) {
            gameObserver.onGameFinished(this);
        }
    }


    private void notifyGameStarted() {
        for (GameObserver gameObserver : gameObservers) {
            gameObserver.onGameStarted(this);
        }
    }

    public interface GameObserver {
        void onGameStarted(Game game);
        void onGameFinished(Game game);
    }

}
