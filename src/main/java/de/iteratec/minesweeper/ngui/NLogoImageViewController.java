package de.iteratec.minesweeper.ngui;

import de.iteratec.minesweeper.Game;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Patrick Hock
 * @created 23.10.2017
 */
public class NLogoImageViewController implements Game.GameObserver {
    private final ImageView logoImageView;

    public NLogoImageViewController(ImageView logoImageView) {
        this.logoImageView = logoImageView;
        setImageView("iteratec-original.png");
    }

    private void setImageView(final String file) {
        Platform.runLater(() ->
                this.logoImageView.setImage(new Image("/" + file)));
    }

    public static NLogoImageViewController create(ImageView logoImageView) {
        return new NLogoImageViewController(logoImageView);
    }

    @Override
    public void onGameStarted(Game game) {
        setImageView("iteratec-original.png");
    }

    @Override
    public void onGameFinished(Game game) {
        if (game.isWon()) {
            setImageView("iteratec-won.png");
        } else {
            setImageView("iteratec-lost.png");
        }
    }
}
