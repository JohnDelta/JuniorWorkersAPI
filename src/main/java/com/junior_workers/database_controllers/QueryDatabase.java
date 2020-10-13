package com.junior_workers.database_controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.junior_workers.bodies.SearchBody;

public class QueryDatabase {
	
	public List<SearchBody> searchAll(String role, String key) throws SQLException {
		
		List<SearchBody> searchBodies = null;
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			if(role.equals("candidate")) {
				
				searchBodies = new ArrayList<SearchBody>();
				
				String query = "SELECT DISTINCT user.firstname, user.lastname, user.email, user.title, user.image_path, user.role " + 
			           " FROM user, education, language, skill, user_has_language, user_has_education, user_has_skill, " +
			            "    experience, profession, education_level, language_level " +
			           " WHERE " +
			               " user.id_user = user_has_language.id_user AND " +
			               " user.id_user = user_has_education.id_user AND " +
			               " user.id_user = user_has_skill.id_user AND " +
			               " education.id_education = user_has_education.id_education AND " +
			               " language.id_language = user_has_language.id_language AND " +
			               " skill.id_skill = user_has_skill.id_skill AND " +
			               " experience.id_user = user.id_user AND " +
			               " profession.id_profession = experience.id_profession AND " +
			               " user_has_education.id_education_level = education_level.id_education_level AND " +
			               " user_has_language.id_language_level = language_level.id_language_level AND " +
			               " user.role = '" + role + "' AND " +
			               " user.availability = 1 AND " +
			               " ( " +
			                   " user.firstname like '%" + key + "%' OR " + 
			                   " user.lastname like '%" + key + "%' OR " + 
			                   " user.bio like '%" + key + "%' OR " +
			                   " user.title like '%" + key + "%' OR " +
			                   " education.title like '%" + key + "%' OR " +
			                   " language.title like '%" + key + "%' OR " +
			                   " skill.title like '%" + key + "%' OR " +
			                   " experience.company like '%" + key + "%' OR " +
			                   " profession.title like '%" + key + "%' " +
			               " )";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					SearchBody searchBody = new SearchBody();
					searchBody.setEmail(resultSet.getString("email"));
					searchBody.setFirstname(resultSet.getString("firstname"));
					searchBody.setLastname(resultSet.getString("lastname"));
					searchBody.setRole(resultSet.getString("role"));
					searchBody.setTitle(resultSet.getString("title"));
					searchBody.setImagePath(resultSet.getString("image_path"));
					searchBodies.add(searchBody);
				}
				
			} else if(role.equals("hirer")) {
				
				searchBodies = new ArrayList<SearchBody>();
				
				String query = "SELECT DISTINCT user.firstname, user.lastname, user.email, user.title, user.image_path, " +
		                " job_post.title AS job_post_title, job_post.description, job_post.id_profession, user.role " + 
		           " FROM user, job_post, profession " +
		           " WHERE " +
		               " user.id_user = job_post.id_user AND " +
		               " profession.id_profession = job_post.id_profession AND " +
		               " user.role = '" + role + "' AND " +
		               " ( " +
		                   " user.firstname like '%" + key + "%' OR " + 
		                   " user.lastname like '%" + key + "%' OR " +
		                   " user.bio like '%" + key + "%' OR " +
		                   " user.title like '%" + key + "%' OR " +
		                   " user.role like '%" + key + "%' OR " +
		                    
		                   " job_post.title like '%" + key + "%' OR " +
		                   " job_post.description like '%" + key + "%' OR " +
		                   " profession.title like '%" + key + "%' " +
		               " )";
			
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					SearchBody searchBody = new SearchBody();
					searchBody.setEmail(resultSet.getString("email"));
					searchBody.setFirstname(resultSet.getString("firstname"));
					searchBody.setLastname(resultSet.getString("lastname"));
					searchBody.setRole(resultSet.getString("role"));
					searchBody.setTitle(resultSet.getString("title"));
					searchBody.setImagePath(resultSet.getString("image_path"));
					searchBody.setDescription(resultSet.getString("description"));
					searchBody.setJobTitle(resultSet.getString("job_post_title"));
					searchBody.setProfessionId(resultSet.getLong("id_profession"));
					searchBodies.add(searchBody);
				}
			}
			
		} catch(Exception e) {
			System.out.println("Error in QueryDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return searchBodies;
	}

}
