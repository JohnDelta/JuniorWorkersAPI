package com.junior_workers.database_controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.junior_workers.models.Experience;
import com.junior_workers.models.Profession;
import com.junior_workers.models.User;

public class ExperienceDatabase {

	public List<Experience> getByUser(User user) throws SQLException {
		
		List<Experience> experiences = new ArrayList<Experience>();
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "SELECT * FROM experience WHERE id_user=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, user.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
			
            while(resultSet.next()) {
            	Profession profession = new ProfessionDatabase().getById(resultSet.getLong("id_profession"));
            	
            	Experience experience = new Experience();
            	experience.setUser(user);
            	experience.setProfession(profession);
            	experience.setExperienceId(resultSet.getLong("id_experience"));
            	experience.setDate(resultSet.getString("date"));
            	experience.setCompany(resultSet.getString("company"));

            	experiences.add(experience);
            }
            
			return experiences;
			
		} catch(Exception e) {
			System.out.println("Error in ExperienceDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return null;
	}
	
	public boolean deleteByUser(User user) throws SQLException {
		
		List<Experience> experiences = getByUser(user);
		
		if(experiences.isEmpty()) {
			return false;
		}
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();
			String query = "";
			PreparedStatement preparedStatement = null;
			
			for(Experience experience: experiences) {
				query = "DELETE FROM experience WHERE id_experience=?";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setLong(1, experience.getExperienceId());
	            preparedStatement.executeUpdate();	
			}
            
		} catch(Exception e) {
			System.out.println("Error in ExperienceDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
				
		return true;
	}
	
	public boolean addToUser(User user, List<Experience> experiences) throws SQLException {

		Connection connection = null;
		try{
			connection = new Database().getConnection();
			String query = "";
			PreparedStatement preparedStatement = null;
			
			for(Experience experience: experiences) {
				query = "INSERT INTO experience (company, date, id_user, id_profession) VALUES(?, ?, ?, ?);";
	            preparedStatement = connection.prepareStatement(query);
	            
	            preparedStatement.setString(1, experience.getCompany());
	            preparedStatement.setString(2, experience.getDate());
	            preparedStatement.setLong(3, experience.getUser().getUserId());
	            preparedStatement.setLong(4, experience.getProfession().getProfessionId());
	            preparedStatement.executeUpdate();
			}
			
		} catch(Exception e) {
			System.out.println("Error in ExperienceDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return true;
	}
	
}
