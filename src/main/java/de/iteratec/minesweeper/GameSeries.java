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

import de.iteratec.minesweeper.api.Board;
import de.iteratec.minesweeper.api.Player;
import de.iteratec.minesweeper.board.RandomBoard;
import de.iteratec.minesweeper.ngui.NNewGameSeriesListener;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a series of games of Minesweeper.
 *
 * @author <a href="mailto:Gerd.Neugebauer@iteratec.de">Gerd Neugebauer</a>
 */
public class GameSeries {


    /**
     * This class collects the game statistics.
     *
     */
    public class GameInfo {

        long time;

        boolean won;

        int width;

        int height;

        int bombs;

        int moves;

        /**
         * Creates a new object.
         *
         * @param time the time
         * @param won the game has been won
         * @param width the width
         * @param height the height
         * @param bombs the number of bombs
         * @param moves the number of moves
         */
        public GameInfo(long time, boolean won, int width, int height,
                int bombs, int moves) {
            this.time = time;
            this.won = won;
            this.width = width;
            this.height = height;
            this.bombs = bombs;
            this.moves = moves;
        }

    }

    /**
     * The field <tt>player</tt> contains the player.
     */
    private Player player;

    /**
     * The field <tt>infos</tt> contains the statistics.
     */
    private List<GameInfo> infos = new ArrayList<>();

    private NNewGameSeriesListener gameSeriesListener;

    private boolean stopped = false;

    /**
     * Creates a new object.
     *
     * @param player the player
     */
    public GameSeries(Player player) {

        this.player = player;
    }

    /**
     * Dump the game statistics to the given writer.
     *
     * @param w the writer
     * 
     * @return this
     * 
     * @throws IOException in case of an error
     */
    public GameSeries dump(Writer w) throws IOException {

        for (GameInfo gi : infos) {
            w.write(gi.won ? "won \t" : "lost\t");
            w.write(Integer.toString(gi.moves));
            w.write('\t');
            w.write(Integer.toString(gi.bombs));
            w.write('\t');
            w.write(Integer.toString(gi.width));
            w.write('\t');
            w.write(Integer.toString(gi.height));
            w.write('\t');
            w.write(Long.toString(gi.time / 1000));
            w.write(" ms\n");

        }
        w.write(
            "-------------------------------------------------------------\n");
        return this;
    }

    /**
     * Get the number of games.
     *
     * @return number of won games
     */
    public int getGames() {

        return infos.size();
    }

    /**
     * Get the number of moves.
     *
     * @return the moves
     */
    public long getMoves() {

        return infos.stream().mapToInt(i -> i.moves).sum();
    }

    /**
     * Get the elapsed time.
     *
     * @return the time
     */
    public long getTime() {

        return infos.stream().mapToLong(i -> i.time).sum();
    }

    /**
     * Get the number of won games.
     *
     * @return number of won games
     */
    public int getWon() {

        return (int) infos.stream().filter(i -> i.won).count();
    }

    /**
     * Sets the listener.
     *
     * @param gameSeriesListener
     */
    public void setGameSeriesListener(NNewGameSeriesListener gameSeriesListener) {
        this.gameSeriesListener = gameSeriesListener;
    }

    /**
     * Stopps a running series.
     * @param stopped
     */
    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public boolean isStopped() {
        return stopped;
    }

    /**
     * Play some games.
     *
     * @param number the number of rounds
     * 
     * @return this
     */
    public GameSeries run(int number) {

        while (number-- > 0 && !stopped) {

            Game game = new Game();
            notifyNewGameInSeries(game);
            long time = System.nanoTime();
            game.play(player);
            time = System.nanoTime() - time;

            Board board = game.getBoard();
            infos.add(new GameInfo(time, game.isWon(), board.getWidth(),
                board.getHeight(), board.getBombs(), game.getMoves()));
        }
        notifGameSeriesFinished();
        setStopped(true);
        return this;
    }

    private void notifGameSeriesFinished() {
        if (gameSeriesListener != null) {
            gameSeriesListener.onGameSeriesFinished();
        }
    }

    private void notifyNewGameInSeries(Game game) {
        if (gameSeriesListener != null) {
            gameSeriesListener.onNewGameInSeries(game);
        }
    }

    /**
     * Set the seed for the random number generator of the board.
     *
     * @param seed the seed
     * 
     * @return this
     */
    public GameSeries setSeed(long seed) {

        RandomBoard.seed(seed);
        return this;
    }
}
