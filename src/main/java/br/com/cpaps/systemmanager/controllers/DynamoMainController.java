package br.com.cpaps.systemmanager.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class DynamoMainController {

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
    public void clickThirdOption(MouseEvent event) throws IOException {
        Label ramalExitButton;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ramalSubView.fxml"));
        Parent dynamoMainRoot = loader.load();

        Stage newStage = new Stage();
        newStage.initStyle(StageStyle.TRANSPARENT);

        Scene scene = new Scene(dynamoMainRoot);
        scene.setFill(null);
        newStage.setScene(scene);
        newStage.setTitle("Dynamo");

        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double windowWidth = 635.0;
        newStage.setX((screenWidth - windowWidth) / 2);
        newStage.setY(0);

        newStage.show();
    }

    @FXML
    public void chama(MouseEvent event){
        System.out.println("chama");
    }

    @FXML
    public void initialize() {
        rootPane.setOpacity(0.6);
        options.setOpacity(0);
    }
}
