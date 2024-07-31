package br.com.cpaps.systemmanager;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DynamoMainController {

    @FXML
    private AnchorPane rootPane;


    @FXML
    private void handleAnchorPaneClick(MouseEvent event) {
        System.out.println("AnchorPane clicked!");
    }

    @FXML
    private void handleMouseEntered(MouseEvent event) {
        System.out.println("Mouse entered AnchorPane!");
        rootPane.setOpacity(1);
    }

    @FXML
    private void handleMouseExited(MouseEvent event) {
        System.out.println("Mouse exited AnchorPane!");
        rootPane.setOpacity(0.6);
    }

    @FXML
    public void initialize() {
        // Initialization logic if needed
        rootPane.setOpacity(0.6);
    }
}
