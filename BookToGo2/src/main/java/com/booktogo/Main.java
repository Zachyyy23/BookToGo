package com.booktogo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;

        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource("/com/booktogo/fxml/login.fxml")
        );

        Scene scene = new Scene(loader.load(), 1100, 700);
        scene.getStylesheets().add(
                Main.class.getResource("/com/booktogo/css/style.css").toExternalForm()
        );

        stage.setTitle("BookToGo");
        stage.setScene(scene);
        stage.show();
    }

    public static void changeScene(String fxmlFile) {
        try {
            String path = "/com/booktogo/fxml/" + fxmlFile;

            System.out.println("Trying to load: " + path);
            System.out.println("Found file? " + Main.class.getResource(path));

            FXMLLoader loader = new FXMLLoader(Main.class.getResource(path));

            Scene scene = new Scene(loader.load(), 1100, 700);
            scene.getStylesheets().add(
                    Main.class.getResource("/com/booktogo/css/style.css").toExternalForm()
            );

            mainStage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}

