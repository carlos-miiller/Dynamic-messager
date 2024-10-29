package br.com.cpaps.systemmanager.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class DynamoMainController {

    @FXML
    private Label dynamoTitle;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Pane options;

    @FXML
    private Pane firstOption;

    @FXML
    private Pane secondOption;

    @FXML
    private Pane thirdOption;

    private Scene majorOrderOverviewScene;
    private Scene ramalsOverviewScene;
    private Stage newStage;
    private boolean stageIsOpen = false;
    private String currentStage;
    private final double screenWidth = Screen.getPrimary().getBounds().getWidth();

    @FXML
    private void handleAnchorPaneClick(MouseEvent event) {

    }

    @FXML
    private void handleMouseEntered(MouseEvent event) {
        rootPane.setOpacity(1);
        options.setOpacity(1);
    }

    @FXML
    private void handleMouseExited(MouseEvent event) {
        rootPane.setOpacity(0.6);
        options.setOpacity(0);
    }

    @FXML
    public void showFirstOption(MouseEvent event){
        firstOption.setOpacity(1);
    }
    @FXML
    public void hideFirstOption(MouseEvent event){
        firstOption.setOpacity(0.5);
    }

    @FXML
    public void showSecondOption(MouseEvent event){
        secondOption.setOpacity(1);
    }

    @FXML
    public void hideSecondOption(MouseEvent event){
        secondOption.setOpacity(0.5);
    }

    @FXML
    public void showThirdOption(MouseEvent event){
        thirdOption.setOpacity(1);
    }

    @FXML
    public void hideThirdOption(MouseEvent event){
        thirdOption.setOpacity(0.5);
    }

    public void clickFirstOption(MouseEvent event) throws IOException {
        if (stageIsOpen) {
            newStage.close();
            newStage = new Stage();
            if (currentStage.equals("ramais")){
                newStage.close();
                currentStage = "";
                return;
            }
            currentStage = "ramais";
        } else {
            newStage = new Stage();
            stageIsOpen = true;
            currentStage = "ramais";
        }

        newStage.initStyle(StageStyle.TRANSPARENT);
        newStage.setScene(majorOrderOverviewScene);
        newStage.setTitle("Dynamo");
        newStage.setY(90);
        newStage.setX(screenWidth-520);
        newStage.show();
    }

    public void clickThirdOption(MouseEvent event) throws IOException {
        if (stageIsOpen) {
            newStage.close();
            newStage = new Stage();
            if (currentStage.equals("major-order")){
                newStage.close();
                currentStage = "";
                return;
            }
            currentStage = "major-order";
        } else {
            newStage = new Stage();
            stageIsOpen = true;
            currentStage = "major-order";
        }
        newStage.initStyle(StageStyle.TRANSPARENT);
        newStage.setScene(ramalsOverviewScene);
        newStage.setTitle("Dynamo");
        newStage.setY(90);
        newStage.setX(screenWidth-520);
        
        newStage.show();
    }

    @FXML
    public void initialize() throws IOException {
        Font customFontHead = Font.loadFont(getClass().getResourceAsStream("/fonts/JetBrainsMono-Bold.ttf"), 22);
        Font customFontMessage = Font.loadFont(getClass().getResourceAsStream("/fonts/JetBrainsMono-Regular.ttf"), 16);
        dynamoTitle.setFont(customFontHead);

        rootPane.setOpacity(0.6);
        options.setOpacity(0);

        majorOrderOverviewScene = new Scene(new FXMLLoader(getClass().getResource("/views/MajorOrderDescription.fxml")).load());
        ramalsOverviewScene = new Scene(new FXMLLoader(getClass().getResource("/views/RamalsDescription.fxml")).load());

        majorOrderOverviewScene.setFill(null);
        ramalsOverviewScene.setFill(null);
    }
}
