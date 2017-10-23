package de.iteratec.minesweeper.ngui;

import de.iteratec.minesweeper.Game;
import javafx.application.Platform;
import javafx.scene.control.Button;

/**
 * @author Patrick Hock
 */
public class NPlayButtonController implements Game.GameObserver {

    private final Button playButton;
    private PlayButtonListener playButtonListener;

    private NPlayButtonController(Button playButton) {
        this.playButton = playButton;
        playButton.setOnMouseClicked(event -> this.onPlayButtonClicked());
    }

    public static NPlayButtonController create(Button playButton) {
        return new NPlayButtonController(playButton);
    }

    void setButtonListener(PlayButtonListener playButtonListener) {
        this.playButtonListener = playButtonListener;
    }

    private void onPlayButtonClicked() {
        if (playButtonListener != null) {
            playButtonListener.onPlayButtonClicked();
        }
    }

    void setTextStop() {
        Platform.runLater(() -> playButton.setText("Play"));
    }

    void setTextPlay() {
        Platform.runLater(() -> playButton.setText("Stop"));
    }

    @Override
    public void onGameStarted(Game game) {

    }

    @Override
    public void onGameFinished(Game game) {

    }

    public void setEnabled(boolean enabled) {
        this.playButton.setDisable(!enabled);
    }


    public interface PlayButtonListener {
        void onPlayButtonClicked();
    }

}
