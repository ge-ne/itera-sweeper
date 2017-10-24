package de.iteratec.minesweeper.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Patrick Hock
 * @created 23.10.2017
 */
public class Config {

    private static Properties properties;

    public static int getFieldDimension() {
        return getIntProperty("field.dimension");
    }

    public static int getDurationClickAnimationInMillis() {
        return getIntProperty("duration.click.animation.millis");
    }

    public static int getDurationWaitAfterGameInMillis() {
        return getIntProperty("duration.wait.after.game.millis");
    }

    public static int getDurationWaitAfterMoveInMillis() {
        return getIntProperty("duration.wait.after.move.millis");
    }

    public static String getProperty(String key) {
        initProperties();
        return properties.getProperty(key);
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }

    private static void initProperties() {
        if (properties != null) {
            return;
        }
        try {
            loadProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadProperties() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("application.properties"));
    }
}
