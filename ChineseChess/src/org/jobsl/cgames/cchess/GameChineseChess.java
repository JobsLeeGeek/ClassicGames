package org.jobsl.cgames.cchess;

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
public class GameChineseChess extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // fxml loader
        URL location = getClass().getResource("fxml/gameChineseChess.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load();
        // init canvas
        Controller controller = fxmlLoader.getController();
        controller.init();
        primaryStage.setTitle("中国象棋");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
