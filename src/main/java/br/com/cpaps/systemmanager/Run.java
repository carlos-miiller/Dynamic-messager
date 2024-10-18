package br.com.cpaps.systemmanager;

import br.com.cpaps.systemmanager.controllers.MessageVerifier;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Run extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            String fxmlFile, fxmlFile2;
            boolean isDynamoMain;

            if (MessageVerifier.haveNew()) {
                fxmlFile = "/views/MajorOrderHeader.fxml";
                fxmlFile2 = "";
                isDynamoMain = false;
            } else {
                fxmlFile = "/views/DynamoMain.fxml";
                fxmlFile2 = "/views/DynamoMain_SecondForm.fxml";

                isDynamoMain = true;
            }

            // Load the primary FXML file
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
                primaryStage.setY(20);

                // Load the second FXML file and show it in a new stage
                FXMLLoader loader2 = new FXMLLoader(getClass().getResource(fxmlFile2));
                Parent root2 = loader2.load();
                Stage secondStage = new Stage();
                secondStage.setX((screenWidth-444)-30);
                secondStage.setY(0);
                secondStage.setHeight(800);
                Scene secondScene = new Scene(root2);
                secondScene.setFill(null);

                secondStage.setScene(secondScene);
                secondStage.initStyle(StageStyle.TRANSPARENT);
                secondStage.setTitle("Dynamo - Second Form");
                secondStage.show();
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
