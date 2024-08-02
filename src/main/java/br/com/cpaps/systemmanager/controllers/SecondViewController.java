package br.com.cpaps.systemmanager.controllers;

import br.com.cpaps.systemmanager.data.JsonFetcher;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


import java.io.IOException;
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

        Font customFontHead = Font.loadFont(getClass().getResourceAsStream("/fonts/JetBrainsMono-Regular.ttf"), 20);
        Font customFontMessage = Font.loadFont(getClass().getResourceAsStream("/fonts/JetBrainsMono-Regular.ttf"), 16);

        titleLabel.setFont(customFontHead);
        messageLabel.setFont(customFontMessage);
        try {
            CompletableFuture<JsonNode> dataFuture = JsonFetcher.fetchData(URL);
            dataFuture.thenAccept(data -> {
                Platform.runLater(() -> {
                    titleLabel.setText(data.path("title").asText());
                    messageLabel.setText(data.path("message").asText());
                });
            }).get();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        rootPane.setOnMouseClicked(event -> openDynamoMain());
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
    private void openDynamoMain() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DynamoMain.fxml"));
            Parent dynamoMainRoot = loader.load();

            Stage newStage = new Stage();
            newStage.initStyle(StageStyle.TRANSPARENT);

            Scene scene = new Scene(dynamoMainRoot);
            scene.setFill(null);
            newStage.setScene(scene);
            newStage.setTitle("Dynamo");

            double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
            double windowWidth = 222;
            newStage.setX((screenWidth - windowWidth) / 2);
            newStage.setY(0);
            newStage.show();

            Stage currentStage = (Stage) rootPane.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
