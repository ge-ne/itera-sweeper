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

package de.iteratec.minesweeper.board;

import de.iteratec.minesweeper.api.Board;

/**
 * This interface describes the modification of a board.
 *
 * @author <a href="mailto:Gerd.Neugebauer@iteratec.de">Gerd Neugebauer</a>
 */
public interface ModifiableBoard extends Board {

    /**
     * Inspect a certain cell on the board. If the cell is occupied by a bomb
     * them <code>false</code> is returned. If the cell does not have a bomb in
     * the neighborhood then the inspection recursively unfolds them too.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * 
     * @return <code>true</code> iff the cell is on the board and does not
     *         contain a bomb
     */
    boolean set(int x, int y);

    /**
     * Places a bomb on a certain cell on the board.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * 
     * @return <code>true</code> iff the cell is on the board and has not
     *         contained a bomb previously
     */
    boolean setBomb(int x, int y);

    /**
     * Check whether there are further cells to be uncovered.
     *
     * @return <code>true</code> iff all free cells have been uncovered
     */
    boolean isCompleted();

}
