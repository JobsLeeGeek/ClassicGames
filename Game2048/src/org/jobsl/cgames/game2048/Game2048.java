package org.jobsl.cgames.game2048;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author JobsLee
 */
public class Game2048 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // fxml loader
        URL location = getClass().getResource("fxml/game2048.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load();
        // init canvas
        Controller controller = fxmlLoader.getController();
        controller.init();
        primaryStage.setTitle("Game-2048");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
