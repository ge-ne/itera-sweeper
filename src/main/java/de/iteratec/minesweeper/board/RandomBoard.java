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

/**
 * This class is a board which is ranomly filled with bombs.
 *
 * @author <a href="mailto:Gerd.Neugebauer@iteratec.de">Gerd Neugebauer</a>
 */
public class RandomBoard extends BaseBoard {

    /**
     * Creates a new object and fill 10% of the board with bombs.
     * The width is randomly selected between 3 and 24.
     * The height is randomly selected between 3 and 24.
     */
    public RandomBoard() {

        this(3 + (int) Math.round(Math.random() * 21), //
            3 + (int) Math.round(Math.random() * 21));
    }

    /**
     * Creates a new object and fill 10% of the board with bombs.
     *
     * @param width the width of the board
     * @param height the height of the board
     *
     * @throws IllegalArgumentException in case of an invalid value for width,
     *         height
     */
    public RandomBoard(int width, int height) {

        this(width, height, width * height / 10);
    }

    /**
     * Creates a new object.
     *
     * @param width the width of the board; it must be greater than 0
     * @param height the height of the board; it must be greater than 0
     * @param bombs the number of bombs to be placed randomly on the board; it
     *        must be greater than 0
     *
     * @throws IllegalArgumentException in case of an invalid value for width,
     *         height, or bombs
     */
    public RandomBoard(int width, int height, int bombs) {

        super(width, height);
        distributeBombs(bombs);
    }

    /**
     * This method distributes a given number of bombs randomly on the board.
     *
     * @param bombs the number of bombs
     */
    public void distributeBombs(int bombs) {

        if (bombs < 0 || bombs > free) {
            throw new IllegalArgumentException("bombs");
        }
        while (bombs > 0) {
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);
            if (set(x, y)) {
                bombs--;
            }
        }
    }

}
