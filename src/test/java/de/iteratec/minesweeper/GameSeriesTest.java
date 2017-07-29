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
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import de.iteratec.minesweeper.players.RandomPlayer;

/**
 * This is a test suite for {@link GameSeries}.
 *
 * @author <a href="mailto:Gerd.Neugebauer@iteratec.de">Gerd Neugebauer</a>
 */
public class GameSeriesTest {

    @Test
    public void test() {

        GameSeries g = new GameSeries(new RandomPlayer(987654321L))
            .setSeed(1234567890L).run(16);
        assertEquals(16, g.getGames());
        assertEquals(71, g.getMoves());
        assertEquals(1, g.getWon());
        assertTrue(g.getTime() > 0);
    }

    @Test
    public void testDump() throws IOException {

        StringWriter w = new StringWriter();
        new GameSeries(new RandomPlayer(987654321L)).setSeed(1234567890L).run(9)
            .dump(w);
        assertEquals("\n\n\n\n\n\n\n\n\n\n",
            w.toString().replaceAll("[^\n]", ""));
    }

}
