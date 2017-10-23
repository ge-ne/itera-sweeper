package de.iteratec.minesweeper.ngui;

import javafx.scene.control.TextField;

/**
 * @author Patrick Hock
 */
public class NBoardDimensionTextFieldsController {

    private static NBoardDimensionTextFieldsController INSTANCE;

    private final TextField xTextField;
    private final TextField yTextField;

    private NBoardDimensionTextFieldsController(TextField xTextField, TextField yTextField) {
        this.xTextField = xTextField;
        this.yTextField = yTextField;
    }

    public static NBoardDimensionTextFieldsController create(TextField xTextField, TextField yTextField) {
        NBoardDimensionTextFieldsController result = new NBoardDimensionTextFieldsController(xTextField, yTextField);
        INSTANCE = result;
        return result;
    }

    static int getMaxWidth() {
        if (INSTANCE == null) {
            throw new RuntimeException("Dimension was not set on GUI");
        }
        String text = INSTANCE.xTextField.getText();
        return Integer.parseInt(text);
    }

    static int getMaxHeight() {
        if (INSTANCE == null) {
            throw new RuntimeException("Dimension was not set on GUI");
        }
        String text = INSTANCE.yTextField.getText();
        return Integer.parseInt(text);
    }
}
