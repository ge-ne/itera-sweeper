package de.iteratec.minesweeper.ngui;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import de.iteratec.minesweeper.api.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Patrick Hock
 */
class NGUIUtils {

    /**
     * Loads all Player-Objects from package de.iteratec.minesweeper.nonHumanPlayers
     *
     * @return List of loaded nonHumanPlayers
     */
    static List<Player> findPlayers() {
        final List<Player> players = new ArrayList<>();
        try {
            final String packageName = "de.iteratec.minesweeper.players";
            final ImmutableSet<ClassPath.ClassInfo> topLevelClasses = ClassPath.from(NGUIUtils.class.getClassLoader()).getTopLevelClasses(packageName);
            for (ClassPath.ClassInfo topLevelClass : topLevelClasses) {
                final Class<?> loadedClass = topLevelClass.load();
                try {
                    Object newInstance = loadedClass.newInstance();

                    if (newInstance instanceof Player) {
                        Player player = (Player) newInstance;
                        players.add(player);
                    }
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }
}
