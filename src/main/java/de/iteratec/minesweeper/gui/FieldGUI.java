package de.iteratec.minesweeper.gui;

import de.iteratec.minesweeper.api.Board;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Conatains the GUI for one single field on the board.
 *
 * @author Patrick Hock
 */
public class FieldGUI extends HBox {

    private static final int DURATION_HUMAN_CLICK_ANIMATION_MILLIS = 400;

    private List<FieldClickListener> listenerList = new ArrayList<>();

    private final int x, y;

    private boolean isFlagged = false;

    private final Button button;

    private boolean isRevealed = false;

    FieldGUI(int x, int y) {
        this.x = x;
        this.y = y;
        button = new Button();
        button.prefWidthProperty().bind(widthProperty());
        button.prefHeightProperty().bind(heightProperty());
        button.setOnMouseClicked(this::mouseClicked);
        getChildren().add(button);
        setAlignment(Pos.CENTER);
        setMaxSize(30, 30);
        setMinSize(30, 30);
    }

    /**
     * Animates a mouse click by higlighting the field for a given time.
     *
     * @param durationInMillis The duration of the hightlighting in field
     */
    void animateMouseClick(int durationInMillis) {
        startAnimation();
        new Thread(() -> endAnimation(durationInMillis)).start();
    }

    /**
     * Reveals a bomb on this field
     */
    void revealBomb() {
        if (isRevealed) {
            return;
        }
        getChildren().remove(button);
        getStyleClass().add("field-bomb");
        requestFocus();
        getStyleClass().add("field-border");

        isRevealed = true;
    }

    /**
     * Reveals a tip on this field
     *
     * @param sense The sense for this field
     */
    void revealTip(Board.Sense sense) {
        if (isRevealed) {
            return;
        }
        if (sense.equals(Board.Sense.BOMB)) {
            revealBomb();
            return;
        }
        if (sense.equals(Board.Sense.OUTSIDE) || sense.equals(Board.Sense.UNKNOWN)) {
            return;
        }
        removeFlag();
        setRevealedStyle(sense.getValue());
        getStyleClass().add("field-border");

        isRevealed = true;
    }


    private void animateMouseClick() {
        animateMouseClick(DURATION_HUMAN_CLICK_ANIMATION_MILLIS);
    }

    private void mouseClicked(MouseEvent event) {
        if (MouseButton.PRIMARY.equals(event.getButton())) {
            if (!isFlagged) {
                onMouseLeftClick();
            }
        }
        if (MouseButton.SECONDARY.equals(event.getButton())) {
            if (!isRevealed()) {
                flag();
            }
        }
        animateMouseClick();
    }

    private void onMouseLeftClick() {
        for (FieldClickListener fieldClickListener : listenerList) {
            fieldClickListener.onLeftClick(x, y);
        }
    }

    private void flag() {
        if (isFlagged) {
            removeFlag();
        } else {
            addFlag();
        }
    }

    private void addFlag() {
        button.getStyleClass().add("field-flag");
        isFlagged = true;
    }

    private void removeFlag() {
        button.getStyleClass().removeAll("field-flag");
        isFlagged = false;
    }

    private void setRevealedStyle(int neighbouringMines) {
        getChildren().remove(button);
        final Label label = new Label();
        label.getStyleClass().add("field-number");
        label.getStyleClass().add(getStlyeForNumber(neighbouringMines));
        if (neighbouringMines != 0) {
            label.setText("" + neighbouringMines);
        }
        this.getChildren().add(label);
        label.requestFocus();
    }

    private void startAnimation() {
        getStyleClass().add("click-animation");
        button.getStyleClass().add("click-animation");
    }

    private void endAnimation(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Platform.runLater(() -> {
            getStyleClass().removeAll("click-animation");
            button.getStyleClass().removeAll("click-animation");
        });
    }

    private boolean isRevealed() {
        return isRevealed;
    }

    private String getStlyeForNumber(int number) {
        switch (number) {
            case 0:
                return "field-zero";
            case 1:
                return "field-one";
            case 2:
                return "field-two";
            case 3:
                return "field-three";
            case 4:
                return "field-four";
            case 5:
                return "field-five";
            case 6:
                return "field-six";
            case 7:
                return "field-seven";
            case 8:
                return "field-eight";
            default:
                return "";
        }
    }

    @Override
    public String toString() {
        return "Field " + x + "/" + y + "(revealed:" + isRevealed() + ")";
    }

    interface FieldClickListener {
        void onLeftClick(int x, int y);
    }

    void addFieldGUIListener(FieldClickListener fieldClickListener) {
        this.listenerList.add(fieldClickListener);
    }
}
