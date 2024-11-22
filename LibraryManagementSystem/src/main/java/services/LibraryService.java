package services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbconnection.DataBaseManager;
import models.Book;

public class LibraryService {
	
	public ArrayList<Book> getAllBooks(){
		ArrayList<Book> books = new ArrayList<>();
		try(Connection conn = DataBaseManager.getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM book");
			while(rs.next()) {
				books.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getBoolean("isavailable")));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	
	public void addBook(Book book) {
        String query = "INSERT INTO book (id,title, author, isavailable) VALUES (?, ?, ?, ?)";
        try (Connection conn = DataBaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
        	pstmt.setInt(1, book.getId());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getAuthor());
            pstmt.setBoolean(4, book.isAvailable());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void deleteBook(int id) {
		try (Connection conn = DataBaseManager.getConnection()){
	             PreparedStatement stmt = conn.prepareStatement(
	            		 "DELETE FROM book where id=?");
	             stmt.setInt(1, id);
	             stmt.executeUpdate();
	             System.out.print("Deleted...");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	public void addRecommendation(int userId, Book book) {
		try (Connection conn = DataBaseManager.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(
            		"INSERT INTO book_recommendations(user_id, book_title, book_author) VALUES (?, ?, ?)");
            stmt.setInt(1, userId);
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.executeUpdate();
       } catch (SQLException e) {
           e.printStackTrace();
       }
	}
	
}
