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

package de.iteratec.minesweeper.players;

import de.iteratec.minesweeper.api.Board;
import de.iteratec.minesweeper.api.Move;
import de.iteratec.minesweeper.api.Player;

/**
 * This Minesweeper player always throws an exception.
 *
 * @author <a href="mailto:Gerd.Neugebauer@iteratec.de">Gerd Neugebauer</a>
 */
public class EPlayer implements Player {

    /**
     * {@inheritDoc}
     *
     * @see de.iteratec.minesweeper.api.Player#move(de.iteratec.minesweeper.api.Board)
     */
    @Override
    public Move move(Board board) {

        throw new RuntimeException();
    }

}
