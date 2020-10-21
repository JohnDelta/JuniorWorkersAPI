package com.junior_workers.database_controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.junior_workers.models.User;
import java.sql.PreparedStatement;

public class UserDatabase {
	
	private String tableName = "user";
	
	public boolean create(User user) throws SQLException {
		
		if(find(user.getEmail()) != null) {
			return false;
		}
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "INSERT INTO " + this.tableName + "(email, password, firstname, lastname, availability, "
					+ "title, bio, role, image_path, video_path, resume_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstname());
            preparedStatement.setString(4, user.getLastname());
            preparedStatement.setInt(5, user.getAvailability());
            preparedStatement.setString(6, user.getTitle());
            preparedStatement.setString(7, user.getBio());
            preparedStatement.setString(8, user.getRole());
            preparedStatement.setString(9, user.getImagePath());
            preparedStatement.setString(10, user.getVideoPath());
            preparedStatement.setString(11, user.getResumePath());
            preparedStatement.executeUpdate();
			
			return true;
			
		} catch(Exception e) {
			System.out.println("Error in UserDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return false;
	}
	
	public boolean delete(User user) throws SQLException {
		
		if(find(user.getEmail()) == null) {
			return false;
		}
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "DELETE FROM " + this.tableName + " WHERE email=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.executeUpdate();
			
			return true;
			
		} catch(Exception e) {
			System.out.println("Error in UserDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return false;
	}
	
	public boolean update(User user) throws SQLException {
		
		User previousDataUser = find(user.getEmail());
		
		if(previousDataUser == null) {
			return false;
		}
		
		// Only these fields update for now
		
		if(user.getFirstname().isEmpty()) {
			user.setFirstname(previousDataUser.getFirstname());
		}
		
		if(user.getLastname().isEmpty()) {
			user.setLastname(previousDataUser.getLastname());
		}
		
		if(user.getAvailability() < 0) {
			user.setAvailability(previousDataUser.getAvailability());
		}
		
		if(user.getTitle().isEmpty()) {
			user.setTitle(previousDataUser.getTitle());
		}
		
		if(user.getBio().isEmpty()) {
			user.setBio(previousDataUser.getBio());
		}
		
//		if(user.getImagePath().isEmpty()) {
//			user.setImagePath(previousDataUser.getImagePath());
//		}
//		
//		if(user.getVideoPath().isEmpty()) {
//			user.setVideoPath(previousDataUser.getVideoPath());
//		}
//		
//		if(user.getResumePath().isEmpty()) {
//			user.setResumePath(previousDataUser.getResumePath());
//		}
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "UPDATE " + this.tableName
					+ " SET firstname=?, lastname=?, availability=?, title=?, bio=?, image_path=?, video_path=?, resume_path=?"
					+ " WHERE email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setInt(3, user.getAvailability());
            preparedStatement.setString(4, user.getTitle());
            preparedStatement.setString(5, user.getBio());
            preparedStatement.setString(6, user.getImagePath());
            preparedStatement.setString(7, user.getVideoPath());
            preparedStatement.setString(8, user.getResumePath());
            preparedStatement.setString(9, user.getEmail());
            preparedStatement.executeUpdate();
			
			return true;
			
		} catch(Exception e) {
			System.out.println("Error in UserDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return false;
	}
	
	public User matchCredentials(User user) throws Exception {
		
		User loggedUser = find(user.getEmail());
		
		if(loggedUser != null) {
			boolean checkPassword = user.getPassword().equals(loggedUser.getPassword());
			if(checkPassword) {
				return loggedUser;
			}
		}
		
		return null;
	}
	
	public User find(String email) throws SQLException {
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();
			String query = "SELECT * FROM " + this.tableName + " WHERE email=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
            	User user = new User();
            	user.setEmail(email);
            	user.setPassword(resultSet.getString("password"));
            	user.setUserId(resultSet.getInt("id_user"));
            	user.setFirstname(resultSet.getString("firstname"));
            	user.setLastname(resultSet.getString("lastname"));
            	user.setAvailability(resultSet.getInt("availability"));
            	user.setTitle(resultSet.getString("title"));
            	user.setBio(resultSet.getString("bio"));
            	user.setRole(resultSet.getString("role"));
            	user.setImagePath(resultSet.getString("image_path"));
            	user.setVideoPath(resultSet.getString("video_path"));
            	user.setResumePath(resultSet.getString("resume_path"));
            	
            	return user;
            }
		
		} catch(Exception e) {
			System.out.println("Error in UserDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return null;
	}
	
	public User find(long userId) throws SQLException {
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();
			String query = "SELECT * FROM " + this.tableName + " WHERE id_user=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
            	User user = new User();
            	user.setUserId(userId);
            	user.setPassword(resultSet.getString("password"));
            	user.setEmail(resultSet.getString("email"));
            	user.setFirstname(resultSet.getString("firstname"));
            	user.setLastname(resultSet.getString("lastname"));
            	user.setAvailability(resultSet.getInt("availability"));
            	user.setTitle(resultSet.getString("title"));
            	user.setBio(resultSet.getString("bio"));
            	user.setRole(resultSet.getString("role"));
            	user.setImagePath(resultSet.getString("image_path"));
            	user.setVideoPath(resultSet.getString("video_path"));
            	user.setResumePath(resultSet.getString("resume_path"));
            	
            	return user;
            }
			
		} catch(Exception e) {
			System.out.println("Error in UserDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return null;
	}
	
}






