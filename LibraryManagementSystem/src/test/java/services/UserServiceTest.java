package services;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dbconnection.DataBaseManager;
import models.Book;
import models.User;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() throws Exception {
        userService = new UserService();
        
        try (Connection conn = DataBaseManager.getConnection(); Statement stmt = conn.createStatement()) {
            // Create the user table
            stmt.execute("CREATE TABLE user (" +
                         "id INT AUTO_INCREMENT PRIMARY KEY, " +
                         "name VARCHAR(100), " +
                         "email VARCHAR(100), " +
                         "password VARCHAR(100))");

            // Create the book_recommendations table
            stmt.execute("CREATE TABLE book_recommendations (" +
                         "id INT AUTO_INCREMENT PRIMARY KEY, " +
                         "user_id INT, " +
                         "book_title VARCHAR(200), " +
                         "book_author VARCHAR(200))");
        }
    }

    @AfterEach
    void tearDown() throws Exception {
        try (Connection conn = DataBaseManager.getConnection(); Statement stmt = conn.createStatement()) {
            // Drop tables after each test
            stmt.execute("DROP TABLE IF EXISTS user");
            stmt.execute("DROP TABLE IF EXISTS book_recommendations");
        }
    }

    @Test
    void testRegisterUser() throws Exception {
        User user = new User(1, "John Doe", "john.doe@example.com", "password123");

        userService.registerUser(user);

        try (Connection conn = DataBaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE email = ?")) {
            stmt.setString(1, "john.doe@example.com");
            var resultSet = stmt.executeQuery();

            assertTrue(resultSet.next());
            assertEquals("John Doe", resultSet.getString("name"));
            assertEquals("john.doe@example.com", resultSet.getString("email"));
            assertEquals("password123", resultSet.getString("password"));
        }
    }

    @Test
    void testLoginUser() throws Exception {
        // Insert a test user
        try (Connection conn = DataBaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO user (name, email, password) VALUES (?, ?, ?)")) {
            stmt.setString(1, "Jane Smith");
            stmt.setString(2, "jane.smith@example.com");
            stmt.setString(3, "securepassword");
            stmt.executeUpdate();
        }

        User user = userService.loginUser("jane.smith@example.com", "securepassword");

        assertNotNull(user);
        assertEquals("Jane Smith", user.getName());
        assertEquals("jane.smith@example.com", user.getEmail());
        assertEquals("securepassword", user.getPassword());
    }

    @Test
    void testFetchRecommendations() throws Exception {
        // Insert a test user and recommendations
        try (Connection conn = DataBaseManager.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("INSERT INTO user (id, name, email, password) VALUES (1, 'Alice', 'alice@example.com', 'pass123')");
            stmt.execute("INSERT INTO book_recommendations (user_id, book_title, book_author) VALUES (1, 'Book 1', 'Author 1')");
            stmt.execute("INSERT INTO book_recommendations (user_id, book_title, book_author) VALUES (1, 'Book 2', 'Author 2')");
        }

        ArrayList<Book> recommendations = userService.fetchRecommendations(1);

        assertEquals(2, recommendations.size());
        assertEquals("Book 1", recommendations.get(0).getTitle());
        assertEquals("Author 1", recommendations.get(0).getAuthor());
        assertEquals("Book 2", recommendations.get(1).getTitle());
        assertEquals("Author 2", recommendations.get(1).getAuthor());
    }
}
