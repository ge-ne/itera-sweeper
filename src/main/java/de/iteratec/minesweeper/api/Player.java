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

package de.iteratec.minesweeper.api;

/**
 * This interface describes the methods of an automatic player of the game of
 * Minesweeper.
 *
 * @author <a href="mailto:Gerd.Neugebauer@iteratec.de">Gerd Neugebauer</a>
 */
public interface Player {

    /**
     * This method is called once at the beginning of a game. The default
     * implementation simply does nothing.
     *
     * @param board the board
     */
    default void startGame(Board board) {

    }

    /**
     * This method is called once at the end of a game. It is invoked in any
     * case being it won or lost. The default implementation simply does
     * nothing.
     * @param won Indicates if the game was won
     */
    default void terminateGame(boolean won) {

    }

    /**
     * This method is invoked to request a move from the player.
     *
     * @param board the board
     * 
     * @return the move, i.e. the coordinates on the board to probe.
     *         Alternatively <code>null</code> can be returned to surrender
     */
    int[] move(Board board);
}
