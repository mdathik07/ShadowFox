package services;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Book;

class LibraryServiceTest {

    private LibraryService libraryService;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        // Set up H2 in-memory database
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        connection.createStatement().execute(
            "CREATE TABLE book (" +
            "id INT PRIMARY KEY, " +
            "title VARCHAR(200), " +
            "author VARCHAR(200), " +
            "isavailable BOOLEAN DEFAULT FALSE)"
        );
        connection.createStatement().execute(
            "CREATE TABLE book_recommendations (" +
            "user_id INT, " +
            "book_title VARCHAR(200), " +
            "book_author VARCHAR(200))"
        );

        libraryService = new LibraryService();
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.createStatement().execute("DROP TABLE book");
        connection.createStatement().execute("DROP TABLE book_recommendations");
        connection.close();
    }

    @Test
    void testGetAllBooks() throws SQLException {
        // Insert sample data
        connection.createStatement().execute("INSERT INTO book (id, title, author, isavailable) VALUES (1, 'Book1', 'Author1', TRUE)");
        connection.createStatement().execute("INSERT INTO book (id, title, author, isavailable) VALUES (2, 'Book2', 'Author2', FALSE)");

        // Execute method
        ArrayList<Book> books = libraryService.getAllBooks();

        // Assertions
        assertEquals(2, books.size());
        assertEquals("Book1", books.get(0).getTitle());
        assertEquals("Book2", books.get(1).getTitle());
        assertTrue(books.get(0).isAvailable());
        assertFalse(books.get(1).isAvailable());
    }

    @Test
    void testAddBook() throws SQLException {
        // Test data
        Book book = new Book(1, "New Book", "New Author", true);

        // Execute method
        libraryService.addBook(book);

        // Verify data in the database
        var rs = connection.createStatement().executeQuery("SELECT * FROM book WHERE id = 1");
        assertTrue(rs.next());
        assertEquals("New Book", rs.getString("title"));
        assertEquals("New Author", rs.getString("author"));
        assertTrue(rs.getBoolean("isavailable"));
    }

    @Test
    void testDeleteBook() throws SQLException {
        // Insert sample data
        connection.createStatement().execute("INSERT INTO book (id, title, author, isavailable) VALUES (1, 'Book1', 'Author1', TRUE)");

        // Execute method
        libraryService.deleteBook(1);

        // Verify data in the database
        var rs = connection.createStatement().executeQuery("SELECT * FROM book WHERE id = 1");
        assertFalse(rs.next());
    }

    @Test
    void testAddRecommendation() throws SQLException {
        // Test data
        Book book = new Book(1, "Recommended Book", "Recommended Author", true);

        // Execute method
        libraryService.addRecommendation(1, book);

        // Verify data in the database
        var rs = connection.createStatement().executeQuery("SELECT * FROM book_recommendations WHERE user_id = 1");
        assertTrue(rs.next());
        assertEquals("Recommended Book", rs.getString("book_title"));
        assertEquals("Recommended Author", rs.getString("book_author"));
    }
}
