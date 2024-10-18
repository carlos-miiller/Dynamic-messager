package br.com.cpaps.systemmanager.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class DynamoSecondController {
    @FXML
    private Pane firstOption;
    @FXML
    private Pane secondOption;
    @FXML
    private Label ramalsTitle, infoTitle;

    @FXML
    private WebView dropdownWebView;
    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane rootPane;

    private Scene ramalsOverviewScene;
    private Stage secondStage;
    private boolean isDropdownVisible = false;

    Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/JetBrainsMono-Bold.ttf"), 22);

    public void setStage(Stage stage) {
        this.secondStage = stage;
    }

    @FXML
    public void showFirstOption(MouseEvent event) {
        firstOption.setOpacity(1);
    }

    @FXML
    public void hideFirstOption(MouseEvent event) {
        firstOption.setOpacity(0.5);
    }

    @FXML
    public void showSecondOption(MouseEvent event) {
        secondOption.setOpacity(1);
    }

    @FXML
    public void hideSecondOption(MouseEvent event) {
        secondOption.setOpacity(0.5);
    }

    @FXML
    public void displayRamals(MouseEvent event) {
        System.out.println("displayRamals triggered");

        Timeline timeline = new Timeline();
        if (isDropdownVisible) {
            // Collapse animation
            KeyValue heightValue = new KeyValue(dropdownWebView.prefHeightProperty(), 0);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), heightValue);
            timeline.getKeyFrames().add(keyFrame);

            timeline.setOnFinished(e -> {
                dropdownWebView.setVisible(false);
                System.out.println("Collapsed: Height = " + dropdownWebView.getHeight());
                adjustStageHeight(400);  // Adjust to a smaller height after collapsing
            });

            isDropdownVisible = false;
        } else {
            dropdownWebView.setVisible(true);

            // Expand animation
            KeyValue heightValue = new KeyValue(dropdownWebView.prefHeightProperty(), 300);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), heightValue);
            timeline.getKeyFrames().add(keyFrame);

            timeline.setOnFinished(e -> {
                System.out.println("Expanded: Height = " + dropdownWebView.getHeight());
                adjustStageHeight(800);  // Adjust to a larger height after expansion
            });

            isDropdownVisible = true;
        }
        timeline.play();
    }

    private void adjustStageHeight(double height) {
        if (secondStage != null) {
            Platform.runLater(() -> {
                if (height > 0) {
                    secondStage.setHeight(height);
                    System.out.println("Stage height adjusted to: " + height);
                }
            });
        }
    }

    public void initialize() throws IOException {
        ramalsOverviewScene = new Scene(new FXMLLoader(getClass().getResource("/views/RamalsDescription.fxml")).load());

        ramalsTitle.setFont(customFont);
        infoTitle.setFont(customFont);

        firstOption.setOpacity(0.5);
        secondOption.setOpacity(0.5);

        // Start with height 0 to make it hidden initially
        dropdownWebView.setPrefHeight(0);
        dropdownWebView.setMinHeight(0);
        dropdownWebView.setMaxHeight(300);
    }
}
