package de.iteratec.minesweeper.gui;

import de.iteratec.minesweeper.api.Board;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The gui containing the minesweeper board.
 *
 * @author Patrick Hock
 */
public class BoardGUI extends VBox implements FieldGUI.FieldClickListener {

    /**
     * Witdh / height of the board.
     */
    private int boardWidth, boardHeight;

    /**
     * Two dimensional array containing the fields of the borad.
     */
    private FieldGUI[][] fields;

    /**
     * Listens to click events performed on a field.
     */
    private FieldGUI.FieldClickListener fieldClickListener;


    /**
     * Loads a new gui presentation of a board
     * @param width the width of the board
     * @param height the height of the board
     */
    void loadNewBoard(int width, int height) {
        this.boardWidth = width;
        this.boardHeight = height;
        getChildren().clear();
        initFields(width, height);
    }

    private void initFields(int width, int height) {
        fields = new FieldGUI[width][height];
        for (int y = 0; y < height; y++) {
            final HBox vBox = new HBox();
            for (int x = 0; x < width; x++) {
                addField(vBox, x, y);
            }
            getChildren().add(vBox);
        }
    }

    /**
     * Animates a mouse click on the board
     * @param x the x position of the field on the board which was clicked
     * @param y the y position of the field on the board which was clicked
     * @param durationInMillis the durataion of the animation
     */
    void animateClick(int x, int y, int durationInMillis) {
        if (x < boardWidth && x >= 0 && y < boardHeight && y >= 0) {
           fields[x][y].animateMouseClick(durationInMillis);
        }
    }

    /**
     * Reveals a tip of given sense on the given position
     * @param x x position on the board
     * @param y y position on the board
     * @param sense the sense of the field
     */
    void revealTip(int x, int y, Board.Sense sense) {
        fields[x][y].revealTip(sense);
    }

    /**
     * Reveals a bomb on the given position.
     * @param x x position on the board
     * @param y y position on the board
     */
    void revealBomb(int x, int y) {
        fields[x][y].revealBomb();
    }

    /**
     * Disables the board (or better: all the fields of the board)
     */
    void disableBoard() {
        for (FieldGUI[] field : fields) {
            for (FieldGUI fieldGUI : field) {
                fieldGUI.setDisable(true);
            }
        }
    }

    /**
     * Triggered when a left click on the a field of given position is done.
     * @param x the x position of the field clicked
     * @param y the y position of the field clicked
     */
    @Override
    public void onLeftClick(int x, int y) {
        fieldClickListener.onLeftClick(x, y);
    }

    /**
     * Adds a new click listener.
     */
    void addFieldClickListener(FieldGUI.FieldClickListener listener) {
        this.fieldClickListener = listener;
    }

    private void addField(HBox vBox, int x, int y) {
        final FieldGUI field = createField(x, y);
        fields[x][y] = field;
        vBox.getChildren().add(field);
    }

    private FieldGUI createField(int x, int y) {
        final FieldGUI field = new FieldGUI(x, y);
        field.addFieldGUIListener(this);
        return field;
    }
}
