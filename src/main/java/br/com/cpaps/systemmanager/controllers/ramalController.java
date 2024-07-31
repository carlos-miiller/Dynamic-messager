package br.com.cpaps.systemmanager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.concurrent.ExecutionException;

public class ramalController {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label ramalExitButton;

    @FXML
    public void initialize() throws ExecutionException, InterruptedException {

    }
    @FXML
    public void close(){
        Stage currentStage = (Stage) rootPane.getScene().getWindow();
        currentStage.close();
    }
    @FXML
    public void displayExit(MouseEvent event){
        ramalExitButton.setOpacity(1);
    }

    @FXML
    public void hideExit(MouseEvent event){
        ramalExitButton.setOpacity(0.5);
    }
}
