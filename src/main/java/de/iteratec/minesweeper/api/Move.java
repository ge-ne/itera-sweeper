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
 * This class represents a coordinate pair in twodimensional space.
 *
 * @author <a href="mailto:Gerd.Neugebauer@iteratec.de">Gerd Neugebauer</a>
 */
public class Move {

    private int x;

    private int y;

    /**
     * Creates a new object.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Move(int x, int y) {

        this.x = x;
        this.y = y;

    }

    /**
     * Getter for x.
     *
     * @return the x coordinate
     */
    public int getX() {

        return x;
    }

    /**
     * Getter for y.
     *
     * @return the y coordinate
     */
    public int getY() {

        return y;
    }

}
