package de.iteratec.minesweeper.ngui;

import de.iteratec.minesweeper.api.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Patrick Hock
 */
public class NPlayersComboBoxController {

    private final ComboBox comboBox;

    private final Map<String, Player> players;

    private NPlayersComboBoxController(ComboBox comboBox, Map<String, Player> players) {
        this.comboBox = comboBox;
        this.players = players;
        this.comboBox.setItems(getPlayersAsObservableList());
        this.comboBox.getSelectionModel().selectFirst();
    }

    public static NPlayersComboBoxController create(ComboBox playersComboBox) {
        List<Player> allPlayers = NGUIUtils.findPlayers();
        Map<String, Player> allPlayersByName = new HashMap<>();
        for (Player player : allPlayers) {
            String playerName = player.getClass().getSimpleName();
            allPlayersByName.put(playerName, player);
        }
        return new NPlayersComboBoxController(playersComboBox, allPlayersByName);
    }

    private ObservableList<String> getPlayersAsObservableList() {
        return FXCollections.observableArrayList(players.keySet());
    }


    Player getSelectedPlayer() {
        String selction = (String) comboBox.getSelectionModel().getSelectedItem();
        return players.get(selction);
    }
}
