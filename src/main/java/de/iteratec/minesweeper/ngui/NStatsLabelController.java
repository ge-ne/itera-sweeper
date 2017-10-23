package de.iteratec.minesweeper.ngui;

import de.iteratec.minesweeper.Game;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Patrick Hock
 */
public class NStatsLabelController implements Game.GameObserver {

    Map<String, Stats> playerStats = new HashMap<>();

    private final Label statsLabel;

    private String currentPlayer = null;

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
        Stats stats = playerStats.get(currentPlayer);
        stats.played++;
        if (game.isWon()) {
            stats.won++;
        }
        updateLabel();
    }

    private void updateLabel() {
        Platform.runLater(() -> {
            Stats stats = playerStats.get(currentPlayer);
            statsLabel.setText("Won " + stats.won + " / " + stats.played);
        });
    }

    public void setPlayerName(String simpleName) {
        this.currentPlayer = simpleName;
        if (!playerStats.containsKey(simpleName)) {
            playerStats.put(simpleName, new Stats());
        }
    }

    private class Stats {
        int played = 0;
        int won = 0;
    }
}
