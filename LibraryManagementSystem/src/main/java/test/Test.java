package test;

import java.util.ArrayList;

import models.Book;
import models.User;
import services.LibraryService;
import services.UserService;

public class Test {
    public static void main(String[] args) {
        UserService userService = new UserService();
        LibraryService libraryService = new LibraryService();
        // Register a user
        User user = new User(1,"John Doe", "john.doe@example.com", "password123");
        userService.registerUser(user);

        // Add book recommendations
        libraryService.addRecommendation(1, new Book(0, "The Catcher in the Rye", "J.D. Salinger", true));
        libraryService.addRecommendation(1, new Book(0, "To Kill a Mockingbird", "Harper Lee", true));

        // Fetch user and print recommendations
        User loggedInUser = userService.loginUser("john.doe@example.com", "password123");
        ArrayList<Book> books = userService.fetchRecommendations(loggedInUser.getId());
        for(Book book: books) {
        	System.out.println(" - " + book.getTitle() + " by " + book.getAuthor());
        }
    }
}
