package com.junior_workers.database_controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.junior_workers.models.JobPost;
import com.junior_workers.models.Profession;
import com.junior_workers.models.User;

public class JobPostDatabase {
	
	public List<JobPost> getByUser(User user) throws SQLException {
		
		List<JobPost> jobPosts = new ArrayList<JobPost>();
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "SELECT * FROM job_post WHERE id_user=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, user.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
			
            while(resultSet.next()) {
            	Profession profession = new ProfessionDatabase().getById(resultSet.getLong("id_profession"));
            	
            	JobPost jobPost = new JobPost();
            	jobPost.setProfession(profession);
            	jobPost.setDescription(resultSet.getString("description"));
            	jobPost.setTitle(resultSet.getString("title"));

            	jobPosts.add(jobPost);
            }
            
			return jobPosts;
			
		} catch(Exception e) {
			System.out.println("Error in JobPostDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return null;
	}
	
	public boolean deleteByUser(User user) throws SQLException {
		
		List<JobPost> jobPosts = getByUser(user);
		
		if(jobPosts.isEmpty()) {
			return false;
		}
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();
			String query = "";
			PreparedStatement preparedStatement = null;
			
			for(JobPost jobPost: jobPosts) {
				query = "DELETE FROM job_post WHERE id_job_post=?";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setLong(1, jobPost.getJobPostId());
	            preparedStatement.executeUpdate();	
			}
            
		} catch(Exception e) {
			System.out.println("Error in JobPostDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
				
		return true;
	}
	
	public boolean addToUser(User user, List<JobPost> jobPosts) throws SQLException {

		Connection connection = null;
		try{
			connection = new Database().getConnection();
			String query = "";
			PreparedStatement preparedStatement = null;
			
			for(JobPost jobPost: jobPosts) {
				query = "INSERT INTO job_post (description, title, id_user, id_profession)"
						+ " VALUES(?, ?, ?, ?);";
	            preparedStatement = connection.prepareStatement(query);
	            
	            preparedStatement.setString(1, jobPost.getDescription());
	            preparedStatement.setString(2, jobPost.getTitle());
	            preparedStatement.setLong(3, user.getUserId());
	            preparedStatement.setLong(4, jobPost.getProfession().getProfessionId());
	            preparedStatement.executeUpdate();
			}
			
		} catch(Exception e) {
			System.out.println("Error in JobPostDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return true;
	}

}
