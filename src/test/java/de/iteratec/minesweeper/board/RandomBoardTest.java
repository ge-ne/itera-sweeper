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
 * TODO gene: missing JavaDoc.
 *
 * @author <a href="mailto:gene@gerd-neugebauer.de">Gerd Neugebauer</a>
 * @version $Revision$
 */
public class RandomBoardTest {

    @Test(expected = IllegalArgumentException.class)
    public void test00() {

        new RandomBoard(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test01() {

        new RandomBoard(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test02() {

        new RandomBoard(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test10() {

        new RandomBoard(1, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test11() {

        new RandomBoard(1, 1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test13() {

        new RandomBoard(2, 3, 7);
    }

    @Test
    public void test20() {

        RandomBoard board = new RandomBoard(1, 1);
        assertEquals(Sense.UNKNOWN, board.get(0, 0));
    }

    @Test
    public void test30() {

        RandomBoard board = new RandomBoard();
        assertTrue(board.getWidth() >= 3);
        assertTrue(board.getWidth() <= 24);
        assertTrue(board.getHeight() >= 3);
        assertTrue(board.getHeight() <= 24);
    }

    @Test
    public void testGet1() {

        RandomBoard board = new RandomBoard(1, 1);
        assertEquals(Sense.OUTSIDE, board.get(-1, 0));
    }

    @Test
    public void testGet2() {

        RandomBoard board = new RandomBoard(1, 1);
        assertEquals(Sense.OUTSIDE, board.get(0, -1));
    }

    @Test
    public void testGet3() {

        RandomBoard board = new RandomBoard(1, 1);
        assertEquals(Sense.OUTSIDE, board.get(1, 0));
    }

    @Test
    public void testGet4() {

        RandomBoard board = new RandomBoard(1, 1);
        assertEquals(Sense.OUTSIDE, board.get(0, 1));
    }

    @Test
    public void testIsCompleted1() {

        assertEquals(false, new RandomBoard(2, 3, 0).isCompleted());
    }

    @Test
    public void testIsCompleted2() {

        assertEquals(true, new RandomBoard(2, 3, 6).isCompleted());
    }

    @Test
    public void testGetWidth1() {

        assertEquals(2, new RandomBoard(2, 3, 0).getWidth());
    }

    @Test
    public void testGetHeight1() {

        assertEquals(3, new RandomBoard(2, 3, 0).getHeight());
    }

}
