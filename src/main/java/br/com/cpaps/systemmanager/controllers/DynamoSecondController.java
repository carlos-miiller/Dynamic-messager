package br.com.cpaps.systemmanager.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    private StackPane stackPane;

    private Scene ramalsOverviewScene;
    private boolean stageIsOpen = false;
    private Stage newStage;
    private String currentStage;
    private final double screenWidth = Screen.getPrimary().getBounds().getWidth();

    Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/JetBrainsMono-Bold.ttf"), 22);

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
    public void clickFirstOption(MouseEvent event) throws IOException {
        if (stageIsOpen) {
            if (currentStage.equals("major-order")) {
                stackPane.setBackground(null);
                currentStage = "";
                stageIsOpen = false;
                newStage.close();
                return;
            } else {
                // Close the current stage if it is not major-order, and prepare to open the new stage
                newStage.setOpacity(0);
                newStage.close();
                newStage = new Stage();
            }
        } else {
            // Create a new stage since no stage is currently open
            newStage = new Stage();
            stageIsOpen = true;
        }

        // Set the current stage identifier
        currentStage = "major-order";
        stackPane.setBackground(Background.fill(Paint.valueOf("rgba(0, 0, 0, 0.458)")));

        ramalsOverviewScene.setFill(null);
        newStage.initStyle(StageStyle.TRANSPARENT);
        newStage.setScene(ramalsOverviewScene);
        newStage.setTitle("Dynamo");
        newStage.setX(screenWidth - 500);
        newStage.setY(122);

        newStage.show();
    }


    public void initialize() throws IOException {
        ramalsOverviewScene = new Scene(new FXMLLoader(getClass().getResource("/views/RamalsDescription.fxml")).load());

        ramalsTitle.setFont(customFont);
        infoTitle.setFont(customFont);

        firstOption.setOpacity(0.5);
        secondOption.setOpacity(0.5);

    }
}
