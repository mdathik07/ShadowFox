package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconnection.DataBaseManager;
import models.Book;
import models.User;

public class UserService {
	
	public void registerUser(User user) {
		try(Connection conn = DataBaseManager.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO user(name, email,password) VALUES (?,?,?)");
			//stmt.setInt(1, user.getId());
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public User loginUser(String email, String password) {
		
		try(Connection conn = DataBaseManager.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(
					"SELECT * FROM user WHERE email=? AND password=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet res = stmt.executeQuery();
			if(res.next()) {
				return new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Book> fetchRecommendations(int userId){
		ArrayList<Book> books = new ArrayList<>();
		try(Connection conn = DataBaseManager.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(
					"SELECT  book_title, book_author FROM book_recommendations WHERE user_id=?");
			stmt.setInt(1, userId);
			ResultSet res = stmt.executeQuery();
			while(res.next()) {
				books.add(new Book(0, res.getString(1), res.getString(2), true));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
}
