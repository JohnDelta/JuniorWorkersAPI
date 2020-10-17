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
				
				String query = "SELECT DISTINCT user.email AS email, user.firstname AS firstname, user.lastname AS lastname,"
						+ " user.role AS role, user.title AS title, user.image_path AS image_path"
						+ " FROM user"
							+ " LEFT JOIN experience ON experience.id_user = user.id_user"
							+ " LEFT JOIN profession ON profession.id_profession = experience.id_profession"
							
							+ " LEFT JOIN user_has_education ON user_has_education.id_user = user.id_user"
							+ " LEFT JOIN education ON education.id_education = user_has_education.id_education"
							
							+ " LEFT JOIN user_has_language ON user_has_language.id_user = user.id_user"
							+ " LEFT JOIN language ON language.id_language = user_has_language.id_language"
							
							+ " LEFT JOIN user_has_skill ON user_has_skill.id_user = user.id_user"
							+ " LEFT JOIN skill ON skill.id_skill = user_has_skill.id_skill"
						+ " WHERE "
							+ "("
								+ " user.role LIKE '"+role+"' AND"
								+ " user.availability = 1 AND"
								+ " user.firstname LIKE '%"+key+"%' OR"
								+ " user.lastname LIKE '%"+key+"%' OR"
								+ " user.title LIKE '%"+key+"%' OR"
								+ " user.bio LIKE '%"+key+"%' OR"
								+ " user.email LIKE '%"+key+"%' OR"
								+ " education.title LIKE '%"+key+"%' OR"
								+ " language.title LIKE '%"+key+"%' OR"
								+ " skill.title LIKE '%"+key+"%' OR"
								+ " profession.title LIKE '%"+key+"%'"
							+ ")";
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
				
				String query = "SELECT DISTINCT user.firstname AS firstname, user.lastname AS lastname,"
								+ " user.email AS email, user.title AS title, user.image_path AS image_path,"
				                + " job_post.title AS job_post_title, job_post.description AS description,"
				                + " job_post.id_profession AS id_profession, user.role AS role" 
			                + " FROM user"
			                	+ " LEFT JOIN job_post ON job_post.id_user = user.id_user"
			                	+ " LEFT JOIN profession ON profession.id_profession = job_post.id_profession"
			                + " WHERE"
			              + " ( "
			              		+ " user.role LIKE '" + role + "' AND"
								+ " user.firstname like '%" + key + "%' OR" 
								+ " user.lastname like '%" + key + "%' OR"
								+ " user.bio like '%" + key + "%' OR"
								+ " user.title like '%" + key + "%' OR"
								+ " user.role like '%" + key + "%' OR"  
								+ " job_post.title like '%" + key + "%' OR"
								+ " job_post.description like '%" + key + "%' OR"
								+ " profession.title like '%" + key + "%' "
			              + " )";
			
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
