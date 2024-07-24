package br.com.cpaps.systemmanager;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
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

public class TransparentWindowWithFadeInFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Get the screen size
        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getBounds().getWidth();
        double screenHeight = screen.getBounds().getHeight();

        // Create a root pane
        Pane root = new Pane();
        root.setStyle("-fx-background-color: #00020E4C;"); // Make the pane transparent

        // Create a gradient fill for the rectangle
        LinearGradient gradient = new LinearGradient(
                0, 0, 1, 0, true, // from left to right
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(34, 60, 200, 0)), // Start transparent
                new Stop(0.1, Color.rgb(13, 22, 59, 1)), // Quickly become opaque
                new Stop(0.9, Color.rgb(13, 22, 59, 1)), // Remain opaque
                new Stop(1, Color.rgb(34, 60, 200, 0)) // End transparent
        );

        // Create a rectangle
        Rectangle rectangle = new Rectangle(1000, 50);
        rectangle.setArcWidth(0);
        rectangle.setArcHeight(0);
        rectangle.setFill(gradient);

        Rectangle rectangle2 = new Rectangle(1000, 400);
        rectangle2.setArcWidth(0);
        rectangle2.setArcHeight(0);
        rectangle2.setFill(gradient);

        // Position the rectangle in the center
        rectangle.setLayoutX((screenWidth - rectangle.getWidth()) / 2); // Center horizontally
        rectangle.setLayoutY(150); // Positioned 150 pixels from the top

        rectangle2.setLayoutX((screenWidth - rectangle.getWidth()) / 2); // Center horizontally
        rectangle2.setLayoutY(202); // Positioned 150 pixels from the top

        // Create a text
        Text text = new Text("RECEBENDO MENSAGEM DA MATRIZ!");
        text.setFont(Font.font("Serif", FontWeight.EXTRA_BOLD, 26));
        text.setFill(Color.WHITE);

        String message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et " +
                "dolore magna aliqua. Ut enim ad minim veni\n\nam, quis nostrud exercitation\n\nullamco laboris nisi ut aliquip " +
                "ex ea commodo consequat.";

        String[] messageChunks = message.split("\\\\\\s+");
        TextFlow textFlow = new TextFlow();
        for (String chunk : messageChunks) {
            Text textChunk = new Text(chunk + " ");
            textChunk.setFont(Font.font("Serif", FontWeight.BOLD, 24));
            textChunk.setFill(Color.WHITE);

            textFlow.getChildren().add(textChunk);
        }
        textFlow.setMaxWidth(800);
        double preferredHeight = textFlow.prefHeight(-1);
        textFlow.setPrefHeight(preferredHeight);
        rectangle2.setHeight(preferredHeight+150);

        // Position the text relative to the rectangle
        text.setX(rectangle.getLayoutX() + (rectangle.getWidth() - text.getBoundsInLocal().getWidth()) / 2);
        text.setY(rectangle.getLayoutY() + (rectangle.getHeight() + text.getBoundsInLocal().getHeight()) / 2 - 5);

        textFlow.setLayoutX(rectangle2.getLayoutX() + 130);
        textFlow.setLayoutY(230);




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

        // Pause for 5 seconds, then apply fade-in transition to second rectangle
        PauseTransition pauseSec = new PauseTransition(Duration.seconds(5));
        pauseSec.setOnFinished(event -> {
            FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(0.4), rectangle2);
            fadeIn2.setFromValue(0);
            fadeIn2.setToValue(1);

            FadeTransition fadeIn3 = new FadeTransition(Duration.seconds(0.4), textFlow);
            fadeIn3.setFromValue(0);
            fadeIn3.setToValue(1);

            root.getChildren().add(rectangle2);
            root.getChildren().add(textFlow);
            fadeIn2.play();
            fadeIn3.play();
        });
        pauseSec.play();




        // Create a button
        Button myButton = new Button("Click me!");
        myButton.setOnAction(e -> {
            // Set up the fade-out animation
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.3), root);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(event -> {
                primaryStage.close();
                System.exit(0);
            });
            fadeOut.play();
        });
        root.getChildren().add(myButton);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
