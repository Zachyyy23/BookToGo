package com.booktogo.controller;

import com.booktogo.Main;
import com.booktogo.model.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class CatalogController {

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> genreFilter;

    @FXML
    private ComboBox<String> availabilityFilter;

    @FXML
    private VBox catalogContent;

    @FXML
    private FlowPane bookResultsContainer;

    private final List<Book> allBooks = new ArrayList<>();

    @FXML
    public void initialize() {
        setupFilters();
        loadSampleBooks();
        displayBooks(allBooks);
    }

    private void setupFilters() {
        genreFilter.getItems().addAll(
                "All Genres",
                "Classic",
                "Drama",
                "Romance",
                "Historical",
                "Adventure",
                "Science Fiction",
                "Fantasy"
        );

        availabilityFilter.getItems().addAll(
                "All",
                "Available",
                "Waitlist",
                "Checked Out",
                "Preview Only"
        );

        genreFilter.setValue("All Genres");
        availabilityFilter.setValue("All");
    }

    private void loadSampleBooks() {
        allBooks.add(new Book(
                "9780743273565",
                "The Great Gatsby",
                "F. Scott Fitzgerald",
                "Classic",
                "Available",
                4.5,
                "A classic novel following Jay Gatsby and his dream of wealth, love, and reinvention during the Jazz Age."
        ));

        allBooks.add(new Book(
                "9780141439662",
                "The Tempest",
                "William Shakespeare",
                "Drama",
                "Available",
                4.2,
                "A dramatic story of magic, betrayal, forgiveness, and power set on a remote island."
        ));

        allBooks.add(new Book(
                "9780141439518",
                "Pride and Prejudice",
                "Jane Austen",
                "Romance",
                "Waitlist",
                4.8,
                "A beloved romance novel exploring manners, marriage, pride, and social expectations."
        ));

        allBooks.add(new Book(
                "9780199232765",
                "War and Peace",
                "Leo Tolstoy",
                "Historical",
                "Checked Out",
                4.7,
                "An epic historical novel about Russian society, war, family, and destiny during the Napoleonic era."
        ));

        allBooks.add(new Book(
                "9780140440584",
                "The Black Tulip",
                "Alexandre Dumas",
                "Classic",
                "Available",
                4.1,
                "A historical adventure centered on rivalry, ambition, and the dream of creating a perfect black tulip."
        ));

        allBooks.add(new Book(
                "9780141441146",
                "Jane Eyre",
                "Charlotte Brontë",
                "Classic",
                "Available",
                4.6,
                "A powerful story of independence, morality, love, and resilience through the life of Jane Eyre."
        ));

        allBooks.add(new Book(
                "9781503280786",
                "Moby-Dick",
                "Herman Melville",
                "Adventure",
                "Preview Only",
                4.0,
                "A seafaring adventure about Captain Ahab’s obsessive pursuit of the great white whale."
        ));

        allBooks.add(new Book(
                "9780553380163",
                "A Brief History of Time",
                "Stephen Hawking",
                "Science Fiction",
                "Available",
                4.4,
                "An accessible exploration of cosmology, black holes, time, and the universe."
        ));

        allBooks.add(new Book(
                "9780544003415",
                "The Hobbit",
                "J.R.R. Tolkien",
                "Fantasy",
                "Waitlist",
                4.9,
                "A fantasy adventure following Bilbo Baggins as he joins a quest filled with danger, courage, and discovery."
        ));
    }

    @FXML
    private void handleSearch() {
        String keyword = searchField.getText().toLowerCase().trim();
        String selectedGenre = genreFilter.getValue();
        String selectedAvailability = availabilityFilter.getValue();

        List<Book> filteredBooks = new ArrayList<>();

        for (Book book : allBooks) {
            boolean matchesKeyword =
                    keyword.isEmpty()
                            || book.getTitle().toLowerCase().contains(keyword)
                            || book.getAuthor().toLowerCase().contains(keyword)
                            || book.getGenre().toLowerCase().contains(keyword);

            boolean matchesGenre =
                    selectedGenre.equals("All Genres")
                            || book.getGenre().equals(selectedGenre);

            boolean matchesAvailability =
                    selectedAvailability.equals("All")
                            || book.getStatus().equals(selectedAvailability);

            if (matchesKeyword && matchesGenre && matchesAvailability) {
                filteredBooks.add(book);
            }
        }

        displayBooks(filteredBooks);
    }

    @FXML
    private void handleReset() {
        searchField.clear();
        genreFilter.setValue("All Genres");
        availabilityFilter.setValue("All");
        displayBooks(allBooks);
    }

    public void showCatalogResults() {
        catalogContent.getChildren().clear();
        catalogContent.getChildren().add(createCatalogMainContent());
        displayBooks(allBooks);
    }

    private VBox createCatalogMainContent() {
        VBox mainBox = new VBox(25);
        mainBox.getStyleClass().add("catalog-page");

        Label title = new Label("Search Book Catalog");
        title.getStyleClass().add("page-title");

        Label subtitle = new Label("Browse, search, borrow, or reserve books from the BookToGo library.");
        subtitle.getStyleClass().add("page-subtitle");

        VBox headerBox = new VBox(8, title, subtitle);

        FlowPane resultsPane = new FlowPane();
        resultsPane.setHgap(20);
        resultsPane.setVgap(20);
        resultsPane.getStyleClass().add("book-section");
        bookResultsContainer = resultsPane;

        Label sectionTitle = new Label("Book Results");
        sectionTitle.getStyleClass().add("section-title");

        mainBox.getChildren().addAll(headerBox, sectionTitle, resultsPane);

        return mainBox;
    }

    private void displayBooks(List<Book> books) {
        bookResultsContainer.getChildren().clear();

        if (books.isEmpty()) {
            Label noResults = new Label("No books found.");
            noResults.getStyleClass().add("no-results-text");
            bookResultsContainer.getChildren().add(noResults);
            return;
        }

        for (Book book : books) {
            bookResultsContainer.getChildren().add(createBookCard(book));
        }
    }

    private VBox createBookCard(Book book) {
        VBox card = new VBox(8);
        card.setAlignment(Pos.TOP_CENTER);
        card.getStyleClass().add("book-card");

        Pane cover = new Pane();
        cover.getStyleClass().add("book-cover");

        Label title = new Label(book.getTitle());
        title.getStyleClass().add("book-title");
        title.setWrapText(true);
        title.setMaxWidth(125);

        Label author = new Label(book.getAuthor());
        author.getStyleClass().add("book-author");
        author.setWrapText(true);
        author.setMaxWidth(125);

        Label genre = new Label(book.getGenre() + " • ⭐ " + book.getAverageRating());
        genre.getStyleClass().add("book-meta");

        Label status = new Label(book.getStatus());
        status.getStyleClass().add(getStatusStyle(book.getStatus()));

        Button actionButton = new Button(getButtonText(book.getStatus()));
        actionButton.getStyleClass().add(getButtonStyle(book.getStatus()));
        actionButton.setOnAction(event -> openBookDetails(book));

        card.setOnMouseClicked(event -> openBookDetails(book));

        card.getChildren().addAll(cover, title, author, genre, status, actionButton);

        return card;
    }

    private void openBookDetails(Book book) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    Main.class.getResource("/com/booktogo/fxml/book-details.fxml")
            );

            Node detailsPage = loader.load();

            BookDetailsController controller = loader.getController();
            controller.setCatalogController(this);
            controller.setBook(book);

            catalogContent.getChildren().clear();
            catalogContent.getChildren().add(detailsPage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getButtonText(String status) {
        return switch (status) {
            case "Available" -> "Borrow";
            case "Waitlist" -> "Join Waitlist";
            case "Checked Out" -> "Checked Out";
            case "Preview Only" -> "Preview Only";
            default -> "View";
        };
    }

    private String getButtonStyle(String status) {
        if (status.equals("Checked Out")) {
            return "book-button-disabled";
        }
        return "book-button";
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