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

import java.io.Serializable;

/**
 * This class contains a base implementation of a Minesweeper board.
 *
 * @author <a href="mailto:Gerd.Neugebauer@iteratec.de">Gerd Neugebauer</a>
 */
public class BaseBoard implements Serializable, ModifiableBoard {

    /**
     * The field <tt>serialVersionUID</tt> contains the version number for
     * serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * This enumeration contains the states of a cell.
     */
    public enum State {
        BOMB, USED, FREE
    }

    private static final Sense[] tip = {Sense.SENSE_0, Sense.SENSE_1,
            Sense.SENSE_2, Sense.SENSE_3, Sense.SENSE_4, Sense.SENSE_5,
            Sense.SENSE_6, Sense.SENSE_7, Sense.SENSE_8};

    /**
     * The field <tt>width</tt> contains the width of the board.
     */
    protected int width;

    /**
     * The field <tt>height</tt> contains the height of the board.
     */
    protected int height;

    protected int free;

    private byte[][] hint;

    private State[][] mark;

    /**
     * Creates a new object.
     *
     * @param width the width of the board; it has to be at least 1
     * @param height the height of the board; it has to be at least 1
     *
     * @throws IllegalArgumentException in case of an invalid value for width,
     *         height
     */
    public BaseBoard(int width, int height) {

        if (width < 1) {
            throw new IllegalArgumentException("width");
        }
        if (height < 1) {
            throw new IllegalArgumentException("height");
        }
        this.width = width;
        this.height = height;
        hint = new byte[width][height];
        mark = new State[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                hint[x][y] = 0;
                mark[x][y] = State.FREE;
            }
        }
        free = width * height;
    }

    /**
     * {@inheritDoc}
     *
     * @see de.iteratec.minesweeper.api.Board#get(int, int)
     */
    @Override
    public Sense get(int x, int y) {

        if (x < 0 || x >= width || y < 0 || y >= height) {
            return Sense.OUTSIDE;
        }
        switch (mark[x][y]) {
            case BOMB:
            case FREE:
                return Sense.UNKNOWN;
            case USED:
                return tip[hint[x][y]];
            default:
                throw new IllegalStateException("This can not happen");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see de.iteratec.minesweeper.api.Board#getHeight()
     */
    @Override
    public int getHeight() {

        return height;
    }

    /**
     * {@inheritDoc}
     *
     * @see de.iteratec.minesweeper.api.Board#getWidth()
     */
    @Override
    public int getWidth() {

        return width;
    }

    /**
     * {@inheritDoc}
     *
     * @see de.iteratec.minesweeper.board.ModifiableBoard#isCompleted()
     */
    @Override
    public boolean isCompleted() {

        return free == 0;
    }

    /**
     * {@inheritDoc}
     *
     * @see de.iteratec.minesweeper.board.ModifiableBoard#set(int, int)
     */
    @Override
    public boolean set(int x, int y) {

        if (x < 0 || x >= width || y < 0 || y >= height
                || mark[x][y] != State.FREE) {
            return false;
        }
        mark[x][y] = State.USED;
        free--;

        // TODO

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @see de.iteratec.minesweeper.board.ModifiableBoard#setBomb(int, int)
     */
    @Override
    public boolean setBomb(int x, int y) {

        if (x < 0 || x >= width || y < 0 || y >= height
                || mark[x][y] != State.FREE) {
            return false;
        }
        mark[x][y] = State.BOMB;
        free--;

        if (x > 0) {
            if (y > 1) {
                hint[x - 1][y - 1]++;
            }
            hint[x - 1][y]++;
            if (y < height - 1) {
                hint[x - 1][y + 1]++;
            }
        }
        if (y > 1) {
            hint[x][y - 1]++;
        }
        if (y < height - 1) {
            hint[x][y + 1]++;
        }
        if (x < width - 1) {
            if (y > 1) {
                hint[x + 1][y - 1]++;
            }
            hint[x + 1][y]++;
            if (y < height - 1) {
                hint[x + 1][y + 1]++;
            }
        }

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();
        for (int y = 0; y < height; y++) {
            buffer.append('|');
            for (int x = 0; x < width; x++) {
                switch (mark[x][y]) {
                    case BOMB:
                        buffer.append('*');
                        break;
                    case FREE:
                        buffer.append(' ');
                        break;
                    case USED:
                        buffer.append('.');
                        break;
                    default:
                        throw new IllegalStateException("This can not happen");
                }
            }
            buffer.append("|\n");
        }

        return buffer.toString();
    }

}
