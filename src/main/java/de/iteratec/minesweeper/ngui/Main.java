package de.iteratec.minesweeper.ngui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Main Application
 *
 * @author Patrick Hock
 */
public class Main extends Application {

    private static Main instance;

    private List<ApplicationStoppedListener> applicationStoppedListeners = new ArrayList<>();

    private Stage stage;

    static Main getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Creates the main window (main_window.fxml).
     * @param primaryStage The primary stage
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        instance = this;
        final Scene scene = createScene();
        this.stage = createStage(primaryStage, scene);
    }

    private Stage createStage(Stage primaryStage, Scene scene) {
        primaryStage.setTitle("iterasweeper");
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("iteratec.png")));
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(NConstants.MENU_WIDTH);
        primaryStage.show();
        return primaryStage;
    }

    private Scene createScene() throws IOException {
        final URL resource = getClass().getClassLoader().getResource("ngui_main.fxml");

        final Parent root = FXMLLoader.load(resource);
        final Scene scene = new Scene(root, NConstants.MENU_WIDTH, NConstants.MENU_HEIGHT);
        scene.getStylesheets().add("minesweeper.css");
        return scene;
    }

    @Override
    public void stop() throws Exception {
        notifyApplicationStopListeners();
        super.stop();
    }

    void registerApplicationStopListener(ApplicationStoppedListener listener) {
        this.applicationStoppedListeners.add(listener);
    }

    private void notifyApplicationStopListeners() {
        for (ApplicationStoppedListener applicationStoppedListener : applicationStoppedListeners) {
            applicationStoppedListener.onApplicationStopped();
        }
    }

    public interface ApplicationStoppedListener {
        void onApplicationStopped();
    }

    Stage getStage() {
        return stage;
    }
}
