package br.com.cpaps.systemmanager;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class TransparentWindowWithFadeInFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Get the screen size
        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getBounds().getWidth();
        double screenHeight = screen.getBounds().getHeight();

        // Create a root pane
        Pane root = new Pane();
        root.setStyle("-fx-background-color: transparent;"); // Make the pane transparent

        // Create a gradient fill for the rectangle
        LinearGradient gradient = new LinearGradient(
                0, 0, 1, 0, true, // from left to right
                javafx.scene.paint.CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(34, 60, 200, 0)), // Start transparent
                new Stop(0.1, Color.rgb(34, 60, 200, 1)), // Quickly become opaque
                new Stop(0.9, Color.rgb(34, 60, 200, 1)), // Remain opaque
                new Stop(1, Color.rgb(34, 60, 200, 0)) // End transparent
        );

        // Create a rectangle
        Rectangle rectangle = new Rectangle(700, 50);
        rectangle.setArcWidth(0);
        rectangle.setArcHeight(0);
        rectangle.setFill(gradient);

        // Position the rectangle in the center
        rectangle.setLayoutX((screenWidth - rectangle.getWidth()) / 2); // Center horizontally
        rectangle.setLayoutY(150); // Positioned 150 pixels from the top

        // Create a text
        Text text = new Text("AVISO IMPORTANTE DA MATRIZ!");
        text.setFont(Font.font("Serif", 24));
        text.setFill(Color.WHITE);

        // Position the text relative to the rectangle
        text.setX(rectangle.getLayoutX() + (rectangle.getWidth() - text.getBoundsInLocal().getWidth()) / 2);
        text.setY(rectangle.getLayoutY() + (rectangle.getHeight() + text.getBoundsInLocal().getHeight()) / 2 - 5);

        // Add the rectangle and text to the root pane
        root.getChildren().addAll(rectangle, text);

        // Create a scene with transparent fill
        Scene scene = new Scene(root, screenWidth, screenHeight, Color.TRANSPARENT);

        // Set up the primary stage
        primaryStage.initStyle(StageStyle.TRANSPARENT); // Make the stage transparent
        primaryStage.setScene(scene);
        primaryStage.show();

        // Apply fade-in transition to the root pane
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.4), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        // Pause for 5 seconds, then apply fade-out transition and exit
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event -> {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.3), root);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(e -> {
                primaryStage.close();
                System.exit(0);
            });
            fadeOut.play();
        });
        pause.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
