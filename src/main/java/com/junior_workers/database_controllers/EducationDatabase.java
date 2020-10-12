package com.junior_workers.database_controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.junior_workers.models.Education;
import com.junior_workers.models.EducationLevel;
import com.junior_workers.models.User;

public class EducationDatabase {

public List<Education> getEducationAll() throws SQLException {
		
		List<Education> allEducation = new ArrayList<Education>();
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "SELECT * FROM education";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
			
            while(resultSet.next()) {
            	Education education = new Education();
            	education.setEducationId(resultSet.getLong("id_education"));
            	education.setTitle(resultSet.getString("title"));
            	allEducation.add(education);
            }
            
			return allEducation;
			
		} catch(Exception e) {
			System.out.println("Error in EducationDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return null;
	}
	
	public List<EducationLevel> getEducationLevelAll() throws SQLException {
		
		List<EducationLevel> educationLevels = new ArrayList<EducationLevel>();
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "SELECT * FROM education_level";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
			
            while(resultSet.next()) {
            	EducationLevel educationLevel = new EducationLevel();
            	educationLevel.setEducationLevelId(resultSet.getLong("id_education_level"));
            	educationLevel.setTitle(resultSet.getString("title"));
            	educationLevels.add(educationLevel);
            }
            
			return educationLevels;
			
		} catch(Exception e) {
			System.out.println("Error in EducationDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return null;
	}
	
	public Education getEducationById(long educationId) throws SQLException {
		
		Education education = null;
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "SELECT * FROM education WHERE id_education=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, educationId);
            ResultSet resultSet = preparedStatement.executeQuery();
			
            if(resultSet.next()) {
            	education = new Education();
            	education.setEducationId(resultSet.getLong("id_education"));
            	education.setTitle(resultSet.getString("title"));
            }
            
		} catch(Exception e) {
			System.out.println("Error in EducationDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return education;
	}
	
	public EducationLevel getEducationLevelById(long educationLevelId) throws SQLException {
		
		EducationLevel educationLevel = null;
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "SELECT * FROM education_level WHERE id_education_level=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, educationLevelId);
            ResultSet resultSet = preparedStatement.executeQuery();
			
            if(resultSet.next()) {
            	educationLevel = new EducationLevel();
            	educationLevel.setEducationLevelId(resultSet.getLong("id_education_level"));
            	educationLevel.setTitle(resultSet.getString("title"));
            }
            
		} catch(Exception e) {
			System.out.println("Error in EducationDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return educationLevel;
	}
	
	public List<Education> getEducationAndLevelByUser(User user) throws SQLException {
		
		List<Integer> userHasEducations = new ArrayList<Integer>();
		List<Integer> userHasEducationLevels = new ArrayList<Integer>();
		List<Education> allEducation = new ArrayList<Education>();
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "SELECT * FROM user_has_education WHERE id_user=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, user.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
			
            while(resultSet.next()) {
            	userHasEducations.add(resultSet.getInt("id_education"));
            	userHasEducationLevels.add(resultSet.getInt("id_education_level"));
            }
            
            int index = 0;
            for(int educationId : userHasEducations) {
            	int educationLevelId = userHasEducationLevels.get(index);
            	
            	Education education = getEducationById(educationId);
            	education.setEducationLevel(getEducationLevelById(educationLevelId));
            	
            	if(education != null) {
            		allEducation.add(education);
            	}
            	index++;
            }
            
			return allEducation;
			
		} catch(Exception e) {
			System.out.println("Error in EducationDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return null;
	}
	
	public boolean deleteByUser(User user) throws SQLException {
		
		List<Education> allEducation = getEducationAndLevelByUser(user);
		
		if(allEducation.isEmpty()) {
			return false;
		}
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();
			String query = "";
			PreparedStatement preparedStatement = null;
			
			for(Education education: allEducation) {
				query = "DELETE FROM user_has_education "
						+ "WHERE id_education=? AND id_user=? AND id_education_level=?";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setLong(1, education.getEducationId());
	            preparedStatement.setLong(2, user.getUserId());
	            preparedStatement.setLong(3, education.getEducationLevel().getEducationLevelId());
	            preparedStatement.executeUpdate();	
			}
            
		} catch(Exception e) {
			System.out.println("Error in EducationDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
				
		return true;
	}
	
	public boolean addToUser(User user, List<Education> allEducation) throws SQLException {

		Connection connection = null;
		try{
			connection = new Database().getConnection();
			String query = "";
			PreparedStatement preparedStatement = null;
			
			for(Education education: allEducation) {
				query = "INSERT INTO user_has_education (id_user, id_education, id_education_level)"
						+ " VALUES(?, ?, ?);";
	            preparedStatement = connection.prepareStatement(query);
	            
	            preparedStatement.setLong(1, user.getUserId());
	            preparedStatement.setLong(2, education.getEducationId());
	            preparedStatement.setLong(3, education.getEducationLevel().getEducationLevelId());
	            preparedStatement.executeUpdate();
			}
			
		} catch(Exception e) {
			System.out.println("Error in EducationDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return true;
	}
	
}
