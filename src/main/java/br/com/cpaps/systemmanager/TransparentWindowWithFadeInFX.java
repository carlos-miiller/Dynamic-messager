package br.com.cpaps.systemmanager;

import br.com.cpaps.systemmanager.controllers.MessageVerifier;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TransparentWindowWithFadeInFX extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            String fxmlFile;
            boolean isDynamoMain;

            if (MessageVerifier.haveNew()) {
                fxmlFile = "/views/MajorOrderHeader.fxml";
                isDynamoMain = false;
            } else {
                fxmlFile = "/views/DynamoMain.fxml";
                isDynamoMain = true;
            }

            loader.setLocation(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.setFill(null);

            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setTitle("Dynamo");
            primaryStage.setScene(scene);

            if (isDynamoMain) {
                // Position DynamoMain at the top center of the screen
                double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
                double windowWidth = 222.0; // Assuming a known width for DynamoMain
                primaryStage.setX((screenWidth - windowWidth) / 2);
                primaryStage.setY(0);
            }

            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
