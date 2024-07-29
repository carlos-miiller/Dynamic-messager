package br.com.cpaps.systemmanager;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


public class MainViewController {
    @FXML
    private Pane mainPane;

    @FXML
    Label orderHeader;

    @FXML
    public void initialize() throws InterruptedException {
        Thread.sleep(1000);
        orderHeader.setText("RECEBENDO MENSAGEM DA MATRIZ");
        mainPane.setOpacity(0);

        applyAnimations(mainPane);
        mainPane.setOnMouseClicked(event -> fadeOutAndSwitchToSecondView());

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/JetBrainsMono-Regular.ttf"), 22);
        orderHeader.setFont(customFont);
    }

    private void applyAnimations(Pane pane) {
        if (pane == null) {
            System.err.println("Pane is null");
            return;
        }

        FadeTransition fadeInTransition = new FadeTransition(Duration.seconds(1.5), pane);
        fadeInTransition.setFromValue(0.0);
        fadeInTransition.setToValue(1.0);

        FadeTransition pulseTransition = new FadeTransition(Duration.seconds(0.5), pane);
        pulseTransition.setFromValue(1.0);
        pulseTransition.setToValue(0.5);
        pulseTransition.setCycleCount(Timeline.INDEFINITE);
        pulseTransition.setAutoReverse(true);

        SequentialTransition sequentialTransition = new SequentialTransition(fadeInTransition, pulseTransition);
        sequentialTransition.play();
    }

    private void fadeOutAndSwitchToSecondView() {
        FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(0.5), mainPane);
        fadeOutTransition.setFromValue(1.0);
        fadeOutTransition.setToValue(0.0);
        fadeOutTransition.setOnFinished(event -> switchToSecondView());

        fadeOutTransition.play();
    }

    private void switchToSecondView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MajorOrderBody.fxml"));
            AnchorPane secondView = loader.load();

            Stage stage = (Stage) mainPane.getScene().getWindow();
            Scene scene = new Scene(secondView);
            scene.setFill(null);

            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
