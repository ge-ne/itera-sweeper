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
import de.iteratec.minesweeper.api.Move;
import de.iteratec.minesweeper.api.Player;
import de.iteratec.minesweeper.board.BoardFactory;
import de.iteratec.minesweeper.board.ModifiableBoard;
import de.iteratec.minesweeper.board.RandomBoard;

/**
 * This class is a driver for a game of Minesweeper.
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

    /**
     * The field <tt>boardFactory</tt> contains the factory to produce a new
     * board.
     */
    private BoardFactory boardFactory = (() -> new RandomBoard());

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
     * @return
     */
    public boolean play(Player player) {

        if (player == null) {
            throw new NullPointerException();
        }

        board = boardFactory.createBoard();

        player.startGame(board);
        try {
            while (!board.isCompleted()) {
                Move move = player.move(board);
                moves++;
                if (moveObserver != null) {
                    moveObserver.observeMove(moves, board, move);
                }
                if (move == null || !board.set(move.getX(), move.getY())) {
                    won = false;
                    return false;
                }
            }

            won = true;
            return true;
        } catch (Exception e) {
            won = false;
            return false;
        } finally {
            player.terminateGame();
        }
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

}
