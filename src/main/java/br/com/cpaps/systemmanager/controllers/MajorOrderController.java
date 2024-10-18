package br.com.cpaps.systemmanager.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Screen;

import java.util.concurrent.ExecutionException;

public class MajorOrderController {
    @FXML
    WebView webView;

    @FXML
    AnchorPane anchonPane;

    @FXML
    public void initialize() throws ExecutionException, InterruptedException {
        String url = getClass().getClassLoader().getResource("static/major_order/index.html").toExternalForm();
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
}
