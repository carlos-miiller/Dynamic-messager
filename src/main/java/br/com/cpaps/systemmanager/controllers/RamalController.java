package br.com.cpaps.systemmanager.controllers;

import br.com.cpaps.systemmanager.data.RamaisApi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.util.concurrent.ExecutionException;

public class RamalController {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label ramalExitButton;

    @FXML
    private TextFlow ramalTextFlow;

    @FXML
    private TextField searchInput;

    @FXML
    WebView webView;

    @FXML
    AnchorPane anchonPane;

    @FXML
    public void initialize() throws ExecutionException, InterruptedException {
        URL url = getClass().getResource("/static/ramals/ramals-overview.html");
        if (url != null) {
            webView.getEngine().load(url.toString());  // Load the HTML file into the WebView
        } else {
            System.out.println("Could not load the HTML file. URL is null.");
        }
        webView.setContextMenuEnabled(false);
        webView.setPrefHeight(Screen.getPrimary().getBounds().getHeight());
        System.out.println(Screen.getPrimary().getBounds().getHeight());
        System.out.println(Screen.getScreens().getFirst().getBounds().getHeight());
    }

    @FXML
    public void close() {
        Stage currentStage = (Stage) rootPane.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    public void displayExit(MouseEvent event) {
        ramalExitButton.setOpacity(1);
    }

    @FXML
    public void hideExit(MouseEvent event) {
        ramalExitButton.setOpacity(0.5);
    }


}