package br.com.cpaps.systemmanager;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SecondViewController {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Text titleLabel;

    @FXML
    private Text messageLabel;

    @FXML
    public void initialize() throws ExecutionException, InterruptedException {
        final String URL = "http://192.168.1.158:8022/api-v1/get-current-message";
        System.out.println("Initializing SecondViewController"); // Debug statement
        rootPane.setOpacity(0); // Initially invisible
        applyFadeInTransition(rootPane);

        try {
            CompletableFuture<JsonFetcher.Data> dataFuture = JsonFetcher.fetchData(URL);

            dataFuture.thenAccept(data -> {
                Platform.runLater(() -> {
                    titleLabel.setText(data.title);
                    messageLabel.setText(data.message);
                });
            }).get(); // This line will block until the CompletableFuture is complete
        } catch (Exception e) {
            // Handle any exceptions during the network request
        }

        rootPane.setOnMouseClicked(event -> {
            System.out.println("Second view clicked, exiting application"); // Debug statement
            Platform.exit();
        });
    }

    private void applyFadeInTransition(AnchorPane pane) {
        if (pane == null) {
            System.err.println("Pane is null"); // Debug statement
            return;
        }
        System.out.println("Applying fade-in transition to second view"); // Debug statement

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), pane);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
}
