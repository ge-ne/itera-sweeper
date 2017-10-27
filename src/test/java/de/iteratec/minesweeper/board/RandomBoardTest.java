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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.iteratec.minesweeper.api.Board.Sense;

/**
 * This is a test suite for the class {@link RandomBoard}.
 *
 * @author <a href="mailto:gene@gerd-neugebauer.de">Gerd Neugebauer</a>
 * @version $Revision$
 */
public class RandomBoardTest {

    /**
     * A illegal size 0x0 leads to an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test00() {

        new RandomBoard(0, 0);
    }

    /**
     * A illegal size 1x0 leads to an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test01() {

        new RandomBoard(1, 0);
    }

    /**
     * A illegal size 0x1 leads to an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test02() {

        new RandomBoard(0, 1);
    }

    /**
     * A illegal number of bombs leads to an exception. Two bombs can not be
     * placed on a 1x1 board.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test10() {

        new RandomBoard(1, 1, 2);
    }

    /**
     * A illegal number of bombs leads to an exception. Bombs can not be
     * negative.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test11() {

        new RandomBoard(1, 1, -1);
    }

    /**
     * A illegal number of bombs leads to an exception. 7 bombs can not be
     * placed on a 2x3 board.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test13() {

        new RandomBoard(2, 3, 7);
    }

    /**
     * A empty board cell which is not set is reported as UNKNOWN.
     */
    @Test
    public void testGet10() {

        RandomBoard board = new RandomBoard(1, 1);
        assertEquals(Sense.UNKNOWN, board.get(0, 0));
    }

    /**
     * A bomb on a board cell which is not set is reported as UNKNOWN.
     */
    @Test
    public void testGet11() {

        RandomBoard board = new RandomBoard(1, 1, 1);
        assertEquals(Sense.UNKNOWN, board.get(0, 0));
    }

    /**
     * Width and height are in the range [3,99].
     */
    @Test
    public void test30() {

        RandomBoard board = new RandomBoard();
        assertTrue(board.getWidth() >= 3);
        assertTrue(board.getWidth() <= 99);
        assertTrue(board.getHeight() >= 3);
        assertTrue(board.getHeight() <= 99);
    }

    /**
     * A cell outside the board is reported as OUTSIDE.
     */
    @Test
    public void testGet30() {

        RandomBoard board = new RandomBoard(1, 1);
        board.set(0, 0);
        assertEquals(Sense.SENSE_0, board.get(0, 0));
    }

    /**
     * A cell outside the board is reported as OUTSIDE.
     */
    @Test
    public void testGet1() {

        RandomBoard board = new RandomBoard(1, 1);
        assertEquals(Sense.OUTSIDE, board.get(-1, 0));
    }

    /**
     * A cell outside the board is reported as OUTSIDE.
     */
    @Test
    public void testGet2() {

        RandomBoard board = new RandomBoard(1, 1);
        assertEquals(Sense.OUTSIDE, board.get(0, -1));
    }

    /**
     * A cell outside the board is reported as OUTSIDE.
     */
    @Test
    public void testGet3() {

        RandomBoard board = new RandomBoard(1, 1);
        assertEquals(Sense.OUTSIDE, board.get(1, 0));
    }

    /**
     * A cell outside the board is reported as OUTSIDE.
     */
    @Test
    public void testGet4() {

        RandomBoard board = new RandomBoard(1, 1);
        assertEquals(Sense.OUTSIDE, board.get(0, 1));
    }

    /**
     * An empty board is not complete.
     */
    @Test
    public void testIsCompleted1() {

        assertEquals(false, new RandomBoard(2, 3, 0).isCompleted());
    }

    /**
     * A filled board is complete.
     */
    @Test
    public void testIsCompleted2() {

        assertEquals(true, new RandomBoard(2, 3, 6).isCompleted());
    }

    /**
     * The width can be queried.
     */
    @Test
    public void testGetWidth1() {

        assertEquals(2, new RandomBoard(2, 3, 0).getWidth());
    }

    /**
     * The height can be queried.
     */
    @Test
    public void testGetHeight1() {

        assertEquals(3, new RandomBoard(2, 3, 0).getHeight());
    }

    /**
     * The string representation can be retrieved.
     */
    @Test
    public void testToString1() {

        assertEquals("| |\n", new RandomBoard(1, 1, 0).toString());
    }

    /**
     * The string representation can be retrieved.
     */
    @Test
    public void testToString2() {

        RandomBoard board = new RandomBoard(1, 1, 0);
        board.set(0, 0);
        assertEquals("|0|\n", board.toString());
    }

    /**
     * The string representation can be retrieved.
     */
    @Test
    public void testToString3() {

        assertEquals("|*|\n", new RandomBoard(1, 1, 1).toString());
    }

    /**
     * The same seed leads to the same board.
     */
    @Test
    public void testStaticRandom1() {

        RandomBoard.seed(1234567890L);
        RandomBoard b1 = new RandomBoard();
        RandomBoard.seed(1234567890L);
        RandomBoard b2 = new RandomBoard();
        assertEquals(b1.toString(), b2.toString());
    }

}
