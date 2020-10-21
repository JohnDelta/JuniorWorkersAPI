package com.junior_workers.database_controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.junior_workers.models.Skill;
import com.junior_workers.models.User;

public class SkillDatabase {
	
	public List<Skill> getAll() throws SQLException {
		
		List<Skill> skills = new ArrayList<Skill>();
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "SELECT * FROM skill";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
			
            while(resultSet.next()) {
            	Skill skill = new Skill();
            	skill.setSkillId(resultSet.getLong("id_skill"));
            	skill.setTitle(resultSet.getString("title"));
            	skills.add(skill);
            }
            
			return skills;
			
		} catch(Exception e) {
			System.out.println("Error in SkillDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return null;
	}
	
	public Skill getById(long skillId) throws SQLException {
		
		Skill skill = null;
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "SELECT * FROM skill WHERE id_skill=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, skillId);
            ResultSet resultSet = preparedStatement.executeQuery();
			
            if(resultSet.next()) {
            	skill = new Skill();
            	skill.setSkillId(resultSet.getLong("id_skill"));
            	skill.setTitle(resultSet.getString("title"));
            }
            
		} catch(Exception e) {
			System.out.println("Error in SkillDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return skill;
	}
	
	public List<Skill> getByUser(User user) throws SQLException {
		
		List<Integer> userHasSkills = new ArrayList<Integer>();
		List<Skill> skills = new ArrayList<Skill>();
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "SELECT * FROM user_has_skill WHERE id_user=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, user.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
			
            while(resultSet.next()) {
            	userHasSkills.add(resultSet.getInt("id_skill"));
            }
            
            for(int skillId : userHasSkills) {
            	Skill skill = getById(skillId);
            	if(skill != null) {
            		skills.add(skill);
            	}
            }
            
			return skills;
			
		} catch(Exception e) {
			System.out.println("Error in SkillDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return null;
	}
	
	public boolean deleteByUser(User user) throws SQLException {
		
		List<Skill> skills = getByUser(user);
		
		if(skills.isEmpty()) {
			return false;
		}
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();
			String query = "";
			PreparedStatement preparedStatement = null;
			
			for(Skill skill: skills) {
				query = "DELETE FROM user_has_skill WHERE id_skill=? AND id_user=?;";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setLong(1, skill.getSkillId());
	            preparedStatement.setLong(2, user.getUserId());
	            preparedStatement.executeUpdate();	
			}
            
		} catch(Exception e) {
			System.out.println("Error in SkillDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
				
		return true;
	}
	
	public boolean addToUser(User user, List<Skill> skills) throws SQLException {

		Connection connection = null;
		try{
			connection = new Database().getConnection();
			String query = "";
			PreparedStatement preparedStatement = null;
			
			for(Skill skill: skills) {
				query = "INSERT INTO user_has_skill (id_user, id_skill) VALUES(?, ?);";
	            preparedStatement = connection.prepareStatement(query);
	            
	            preparedStatement.setLong(1, user.getUserId());
	            preparedStatement.setLong(2, skill.getSkillId());
	            preparedStatement.executeUpdate();
			}
			
		} catch(Exception e) {
			System.out.println("Error in SkillDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return true;
	}

}


















