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

package de.iteratec.minesweeper.main;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import de.iteratec.minesweeper.GameSeries;
import de.iteratec.minesweeper.api.Player;

/**
 * TODO gene: missing JavaDoc.
 *
 * @author <a href="mailto:Gerd.Neugebauer@iteratec.de">Gerd Neugebauer</a>
 */
public class IteraSweeperMain {

    /**
     * The field <tt>OPTS</tt> contains the description of the command line
     * options.
     */
    private static final Options OPTS;
    static {
        OPTS = new Options();
        OPTS.addOption("n", "number", true, "the number of iterations");
        OPTS.addOption("c", "class", true, "the player class");
    }

    /**
     * The command line interface.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.exit(run(args));
    }

    /**
     * TODO gene: missing JavaDoc
     *
     * @param args the command line arguments
     * 
     * @return the exit code
     */
    private static int run(String[] args) {

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(OPTS, args);
        } catch (ParseException e) {
            return -1;
        }

        Player player = null;
        GameSeries series = new GameSeries(player);
        series.run(64);
        return 0;
    }

}
