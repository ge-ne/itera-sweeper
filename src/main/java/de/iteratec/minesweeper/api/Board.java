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
 * This interface describes the capabilities needed to inspect a board for the
 * game of Minesweeper.
 *
 * @author <a href="mailto:Gerd.Neugebauer@iteratec.de">Gerd Neugebauer</a>
 */
public interface Board {

    /**
     * This enumeration contains the status values which can be senses on a
     * board for the game of Minesweeper.
     *
     */
    enum Sense {
        /**
         * The value <tt>OUTSIDE</tt> contains the indicator for a cell outside
         * the board.
         */
        OUTSIDE,
        /**
         * The value <tt>UNKNOWN</tt> contains the indicator for a cell with
         * unknown status.
         */
        UNKNOWN,
        /**
         * The value <tt>BOMB</tt> contains the indicator for a cell with a
         * bomb.
         */
        BOMB,
        /**
         * The value <tt>SENSE_0</tt> contains the indicator for a cell with 0
         * bomb in the neighborhood.
         */
        SENSE_0,
        /**
         * The value <tt>SENSE_1</tt> contains the indicator for a cell with 1
         * bomb in the neighborhood.
         */
        SENSE_1,
        /**
         * The value <tt>SENSE_2</tt> contains the indicator for a cell with 2
         * bomb in the neighborhood.
         */
        SENSE_2,
        /**
         * The value <tt>SENSE_3</tt> contains the indicator for a cell with 3
         * bomb in the neighborhood.
         */
        SENSE_3,
        /**
         * The value <tt>SENSE_4</tt> contains the indicator for a cell with 4
         * bomb in the neighborhood.
         */
        SENSE_4,
        /**
         * The value <tt>SENSE_5</tt> contains the indicator for a cell with 5
         * bomb in the neighborhood.
         */
        SENSE_5,
        /**
         * The value <tt>SENSE_6</tt> contains the indicator for a cell with 6
         * bomb in the neighborhood.
         */
        SENSE_6,
        /**
         * The value <tt>SENSE_7</tt> contains the indicator for a cell with 7
         * bomb in the neighborhood.
         */
        SENSE_7,
        /**
         * The value <tt>SENSE_8</tt> contains the indicator for a cell with 8
         * bomb in the neighborhood.
         */
        SENSE_8
    }

    /**
     * This is the getter for the width of the field. Any cell with an x
     * coordinate outside of the range 0..(width-1) will be reported as ${link
     * Sense.OUTSIDE}.
     *
     * @return the width of the field
     */
    int getWidth();

    /**
     * This is the getter for the height of the field. Any cell with an y
     * coordinate outside of the range 0..(height-1) will be reported as ${link
     * Sense.OUTSIDE}.
     *
     * @return
     */
    int getHeight();

    /**
     * Getter for the content of a cell in the field.
     *
     * @param x the x coordinate to be queried
     * @param y the y coordinate to be queried
     * 
     * @return the content of the cell
     */
    Sense get(int x, int y);
}
