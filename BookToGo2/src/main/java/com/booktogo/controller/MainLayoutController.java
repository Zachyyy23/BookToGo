package com.booktogo.controller;

import com.booktogo.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class MainLayoutController {

    @FXML
    private StackPane contentArea;

    @FXML
    public void initialize() {
        loadPage("dashboard.fxml");
    }

    @FXML
    private void showDashboard() {
        loadPage("dashboard.fxml");
    }

    @FXML
    private void showCatalog() {
        loadPage("catalog.fxml");
    }

    @FXML
    private void logout() {
        Main.changeScene("login.fxml");
    }

    private void loadPage(String fxmlFile) {
        try {
            Node page = FXMLLoader.load(
                    Main.class.getResource("/com/booktogo/fxml/" + fxmlFile)
            );

            contentArea.getChildren().clear();
            contentArea.getChildren().add(page);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}