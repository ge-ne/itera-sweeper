package de.iteratec.minesweeper.ngui;

import de.iteratec.minesweeper.GameSeries;
import de.iteratec.minesweeper.api.Player;

/**
 * @author Patrick Hock
 */
public class NGameSeriesController {
    private int seed;
    private Player player;
    private NNewGameSeriesListener newGameInSeriesListener;
    private GameSeries gameSeries;
    private int runs;

    public static NGameSeriesController create() {
        return new NGameSeriesController();
    }

    void setSeed(int seed) {
        this.seed = seed;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    void setRuns(int runs) {
        this.runs = runs;
    }

    void setGameSeriesListener(NNewGameSeriesListener newGameInSeriesListener) {
        this.newGameInSeriesListener = newGameInSeriesListener;
    }

    void startNewGameSeries() {
        stopGameSeries();
        gameSeries = new GameSeries(player);
        gameSeries.setSeed(seed);
        gameSeries.setGameSeriesListener(newGameInSeriesListener);
        new Thread(() -> gameSeries.run(runs)).start();
    }

    void stopGameSeries() {
        if (gameSeries != null && !gameSeries.isStopped()) {
            gameSeries.setStopped(true);
        }
    }

    boolean isGameSeriesRunning() {
        return gameSeries != null && ! gameSeries.isStopped();
    }
}
