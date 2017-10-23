package de.iteratec.minesweeper.ngui;

import de.iteratec.minesweeper.Game;
import de.iteratec.minesweeper.GameSeries;
import de.iteratec.minesweeper.api.Player;

/**
 * @author Patrick Hock
 */
public class NGameSeriesController implements NNewGameSeriesListener {


    public enum SeriesState {
        /** Series is running **/
        RUNNING,
        /** Stopping but player finishes his move **/
        STOPPING,
        /** Series was stopped **/
        STOPPED
    }

    private SeriesState state = SeriesState.STOPPED;
    private int seed;
    private Player player;
    private NNewGameSeriesListener gameInSeriesListener;
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
        this.gameInSeriesListener = newGameInSeriesListener;
    }

    void startNewGameSeries() {
        stopGameSeries();
        this.state = SeriesState.RUNNING;
        gameSeries = new GameSeries(player);
        gameSeries.setSeed(seed);
        gameSeries.setGameSeriesListener(this);
        new Thread(() -> gameSeries.run(runs)).start();
    }

    void stopGameSeries() {
        if (gameSeries != null && !gameSeries.isStopped()) {
            this.state = SeriesState.STOPPING;
            gameSeries.setStopped(true);
        }
    }

    boolean isGameSeriesRunning() {
        return state == SeriesState.RUNNING;
    }


    @Override
    public void onNewGameInSeries(Game game) {
        gameInSeriesListener.onNewGameInSeries(game);
    }

    @Override
    public void onGameSeriesFinished() {
        this.state = SeriesState.STOPPED;
        gameInSeriesListener.onGameSeriesFinished();
    }
}
