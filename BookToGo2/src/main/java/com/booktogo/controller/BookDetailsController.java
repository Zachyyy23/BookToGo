package com.booktogo.controller;

import com.booktogo.model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class BookDetailsController {

    @FXML
    private Label titleLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label isbnLabel;

    @FXML
    private Label genreLabel;

    @FXML
    private Label ratingLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Button primaryActionButton;

    @FXML
    private Pane coverPane;

    private CatalogController catalogController;

    public void setCatalogController(CatalogController catalogController) {
        this.catalogController = catalogController;
    }

    public void setBook(Book book) {
        titleLabel.setText(book.getTitle());
        authorLabel.setText("by " + book.getAuthor());
        isbnLabel.setText("ISBN: " + book.getIsbn());
        genreLabel.setText("Genre: " + book.getGenre());
        ratingLabel.setText("Rating: ⭐ " + book.getAverageRating());
        statusLabel.setText(book.getStatus());
        descriptionLabel.setText(book.getDescription());

        statusLabel.getStyleClass().clear();
        statusLabel.getStyleClass().add(getStatusStyle(book.getStatus()));

        primaryActionButton.setText(getButtonText(book.getStatus()));

        primaryActionButton.getStyleClass().clear();
        if (book.getStatus().equals("Checked Out")) {
            primaryActionButton.getStyleClass().add("details-button-disabled");
            primaryActionButton.setDisable(true);
        } else {
            primaryActionButton.getStyleClass().add("details-primary-button");
            primaryActionButton.setDisable(false);
        }
    }

    @FXML
    private void handleBack() {
        if (catalogController != null) {
            catalogController.showCatalogResults();
        }
    }

    private String getButtonText(String status) {
        return switch (status) {
            case "Available" -> "Borrow Book";
            case "Waitlist" -> "Join Waitlist";
            case "Checked Out" -> "Checked Out";
            case "Preview Only" -> "Preview Book";
            default -> "View Book";
        };
    }

    private String getStatusStyle(String status) {
        return switch (status) {
            case "Available" -> "status-available";
            case "Waitlist" -> "status-waitlist";
            case "Checked Out" -> "status-checkedout";
            case "Preview Only" -> "status-preview";
            default -> "status-preview";
        };
    }
}