package br.com.cpaps.systemmanager;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

public class TransparentWindowWithFadeInFX extends Application {

    @Override
    public void start(Stage primaryStage) throws ExecutionException, InterruptedException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MajorOrderHeader.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            scene.setFill(null);

            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setTitle("FXML Example");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
