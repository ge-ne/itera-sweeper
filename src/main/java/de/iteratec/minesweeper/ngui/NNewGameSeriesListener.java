package de.iteratec.minesweeper.ngui;

import de.iteratec.minesweeper.Game;

/**
 * @author Patrick Hock
 * @created 06.10.2017
 */
public interface NNewGameSeriesListener {

    void onNewGameInSeries(Game game);

    void onGameSeriesFinished();
}
