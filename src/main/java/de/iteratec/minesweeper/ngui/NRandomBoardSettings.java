package de.iteratec.minesweeper.ngui;

/**
 * @author Patrick Hock
 * @created 22.10.2017
 */
public class NRandomBoardSettings {

    private static final int DEFAULT_MAX_WIDTH = 30;
    private static final int DEFAULT_MAX_HEIGHT = 30;

    public static int getMaxWidth() {
        try {
            return NBoardDimensionTextFieldsController.getMaxWidth();
        } catch (RuntimeException e) {
            return DEFAULT_MAX_WIDTH;
        }
    }

    public static int getMaxHeight() {
        try {
            return NBoardDimensionTextFieldsController.getMaxHeight();
        } catch (RuntimeException e) {
            return DEFAULT_MAX_HEIGHT;
        }
    }
}
