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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.iteratec.minesweeper.board.BaseBoard;
import de.iteratec.minesweeper.players.APlayer;
import de.iteratec.minesweeper.players.EPlayer;
import de.iteratec.minesweeper.players.RandomPlayer;

/**
 * This is a JUnit test suite for the {@link Game} class.
 *
 * @author <a href="mailto:gene@gerd-neugebauer.de">Gerd Neugebauer</a>
 */
public class GameTest {

    /**
     * <code>null</code> as argument to play throws an exception.
     */
    @Test(expected = NullPointerException.class)
    public void testPlayNull() {

        new Game().play(null);
    }

    /**
     *
     */
    @Test
    public void testRandom1() {

        Game game = new Game();
        game.play(new RandomPlayer());
        assertTrue(game.getMoves() > 0);
    }

    /**
     * A surrendering player does not play long.
     */
    @Test
    public void testA1() {

        Game game = new Game();
        game.play(new APlayer());
        assertEquals(1, game.getMoves());
    }

    /**
     * An Exception in the player makes counting the game as lost.
     */
    @Test
    public void testE1() {

        Game game = new Game();
        game.play(new EPlayer());
        assertEquals(0, game.getMoves());
        assertFalse(game.isWon());
    }

    /**
     * A 1x1 board leads to exactly 1 move.
     */
    @Test
    public void test10() {

        Game game = new Game().setBoardFactory(() -> new BaseBoard(1, 1));
        game.play(new RandomPlayer());
        assertEquals(1, game.getMoves());
        assertNotNull(game.getBoard());
    }

    /**
     * The move observer is called for each move.
     */
    @Test
    public void test11() {

        int[] count = {0};
        Game game = new Game().setMoveObserver((a, b, c) -> count[0]++);
        game.play(new RandomPlayer());
        assertEquals(game.getMoves(), count[0]);
    }

    /**
     * The move observer is called for each move.
     */
    @Test
    public void test20() {

        int[] count = {0};
        Game game = new Game().setMoveObserver((a, b, c) -> count[0]++);
        game.play(new RandomPlayer());
        assertEquals(game.getMoves(), count[0]);
    }

}
