package br.com.cpaps.systemmanager;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
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
    private Pane messagePane;

    @FXML
    public void initialize() throws ExecutionException, InterruptedException {
        final String URL = "http://192.168.1.158:8022/api-v1/get-current-message";
        rootPane.setOpacity(0);
        messagePane.setOpacity(0);
        applyFadeInTransition(rootPane);

        Font customFontHead = Font.loadFont(getClass().getResourceAsStream("/JetBrainsMono-Regular.ttf"), 20);
        Font customFontMessage = Font.loadFont(getClass().getResourceAsStream("/JetBrainsMono-Regular.ttf"), 16);

        titleLabel.setFont(customFontHead);
        messageLabel.setFont(customFontMessage);
        try {
            CompletableFuture<JsonFetcher.Data> dataFuture = JsonFetcher.fetchData(URL);

            dataFuture.thenAccept(data -> {
                Platform.runLater(() -> {
                    titleLabel.setText(data.title);
                    messageLabel.setText(data.message);
                });
            }).get();
        } catch (Exception e) {
            // Incluir tratamento de excessões
        }
        rootPane.setOnMouseClicked(event -> {
            Platform.exit();
        });
    }

    private void applyFadeInTransition(AnchorPane pane) {
        if (pane == null) {
            System.err.println("Pane is null");
            return;
        }
        System.out.println("Applying fade-in transition to second view");

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.3), pane);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setOnFinished(actionEvent -> applyFadeInMessagePane(messagePane));
        fadeTransition.play();
    }
    private void applyFadeInMessagePane(Pane messagePane) {
        if (messagePane == null) {
            System.err.println("Pane is null");
            return;
        }

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), messagePane);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
}
