package de.iteratec.minesweeper.ngui;

import javafx.application.Platform;
import javafx.scene.control.Button;

/**
 * @author Patrick Hock
 */
public class NPlayButtonController {

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

    void seriesHasFinishedOrStopped() {
        Platform.runLater(() -> playButton.setText("Play"));
    }

    void seriesHasBegun() {
        Platform.runLater(() -> playButton.setText("Stop"));
    }


    public interface PlayButtonListener {
        void onPlayButtonClicked();
    }

}
