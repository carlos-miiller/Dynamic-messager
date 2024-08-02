package br.com.cpaps.systemmanager.controllers;

import br.com.cpaps.systemmanager.data.RamaisApi;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.concurrent.ExecutionException;

public class ramalController {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label ramalExitButton;

    @FXML
    private TextFlow ramalTextFlow;


    @FXML
    public void initialize() throws ExecutionException, InterruptedException {
        RamaisApi apiClient = new RamaisApi();
        JsonNode response = apiClient.fetchAllRamals();


        if (response != null) {
            for (JsonNode ramalNode : response) {
                String nome = ramalNode.path("nome").asText();
                String ramal = ramalNode.path("ramal").asText();

                Text nameText = new Text(nome);
                nameText.setFont(new Font("JetBrains Mono ExtraBold", 15));
                nameText.setFill(javafx.scene.paint.Color.WHITE);

                Text numberText = new Text(ramal);
                numberText.setFont(new Font("JetBrains Mono ExtraBold", 15));
                numberText.setFill(javafx.scene.paint.Color.WHITE);
                numberText.setTextAlignment(TextAlignment.LEFT);

                HBox hBox = new HBox();
                hBox.setOpacity(0.8);
                hBox.setSpacing(10);
                hBox.setMaxHeight(200);
                hBox.setId("ramalSet");

                HBox nameBox = new HBox(nameText);
                nameBox.setPrefWidth(210);
                nameBox.setMaxWidth(210);
                nameBox.setPrefHeight(10);
                nameBox.setAlignment(Pos.CENTER_LEFT);

                nameText.wrappingWidthProperty().bind(nameBox.widthProperty());

                HBox numberBox = new HBox(numberText);
                numberBox.setPrefWidth(60);
                numberBox.setPrefHeight(10);
                numberBox.setAlignment(Pos.CENTER_LEFT);

                numberText.wrappingWidthProperty().bind(numberBox.widthProperty());

                hBox.getChildren().addAll(nameBox, numberBox);
                hBox.setOnMouseClicked(event -> {
                    apiClient.pushCall(ramal);
                    close();
                });
                hBox.setOnMouseEntered(event -> {
                    hBox.setOpacity(1);
                    hBox.setCursor(Cursor.HAND);
                });
                hBox.setOnMouseExited(event -> {
                    hBox.setOpacity(0.8);
                    hBox.setCursor(Cursor.HAND);
                });
                ramalTextFlow.getChildren().add(hBox);
            }
        }
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
