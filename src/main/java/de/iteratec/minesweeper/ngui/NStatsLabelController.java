package de.iteratec.minesweeper.ngui;

import de.iteratec.minesweeper.Game;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 * @author Patrick Hock
 */
public class NStatsLabelController implements Game.GameObserver {

    private int gamesWonCount = 0;
    private int gamesCount = 0;

    private final Label statsLabel;

    private NStatsLabelController(Label statsLabel) {
        this.statsLabel = statsLabel;
    }

    public static NStatsLabelController create(Label statsLabel) {
        return new NStatsLabelController(statsLabel);
    }

    @Override
    public void onGameStarted(Game game) {
        updateLabel();
    }

    @Override
    public void onGameFinished(Game game) {
        gamesCount++;
        if (game.isWon()) {
            gamesWonCount++;
        }
        updateLabel();
    }

    private void updateLabel() {
        Platform.runLater(() -> statsLabel.setText("Won " + gamesWonCount + " / " + gamesCount));
    }
}
