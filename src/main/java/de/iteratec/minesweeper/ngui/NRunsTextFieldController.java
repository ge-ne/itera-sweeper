package de.iteratec.minesweeper.ngui;

import javafx.scene.control.TextField;

/**
 * @author Patrick Hock
 */
public class NRunsTextFieldController {
    private final TextField runsTextField;

    private NRunsTextFieldController(TextField runsTextField) {
        this.runsTextField = runsTextField;
    }

    public static NRunsTextFieldController create(TextField runsTextField) {
        return new NRunsTextFieldController(runsTextField);
    }

    int getRuns() {
        String text = this.runsTextField.getText();
        return Integer.parseInt(text);
    }
}
