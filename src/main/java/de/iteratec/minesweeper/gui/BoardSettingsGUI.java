package de.iteratec.minesweeper.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * GUI for choosing the board settings (dimension of fields, number of bombs)
 *
 * @author Patrick Hock
 */
public class BoardSettingsGUI extends HBox implements Initializable {

    @FXML
    private TextField widthTextField;

    @FXML
    private TextField heightTextField;

    @FXML
    private TextField bombsTextField;

    public BoardSettingsGUI() {
        final URL boardSettingsFxmlFile = getClass().getClassLoader().getResource("board_settings.fxml");
        final FXMLLoader fxmlLoader = new FXMLLoader(boardSettingsFxmlFile);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    int getBoardWidth() {
        return Integer.parseInt(widthTextField.getText());
    }

    int getBoardHeight() {
        return Integer.parseInt(heightTextField.getText());
    }

    int getNumberOfBombs() {
        return Integer.parseInt(bombsTextField.getText());
    }
}