package de.iteratec.minesweeper.ngui;

import de.iteratec.minesweeper.api.Player;
import de.iteratec.minesweeper.players.HumanPlayer;

/**
 * @author Patrick Hock
 */
public class NPlayerController implements NFieldGUI.FieldClickListener {
    private Player player;

    public static NPlayerController create() {
        return new NPlayerController();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    boolean isHumanPlayer() {
        return this.player != null && this.player.getClass().equals(HumanPlayer.class);
    }

    @Override
    public void onLeftClick(int x, int y) {
        if (isHumanPlayer()) {
            ((HumanPlayer) player).onLeftClick(x, y);
        }
    }

    void onGameFinished() {
        if (isHumanPlayer()) {
            ((HumanPlayer) player).setTerminated(true);
        }
    }

}
