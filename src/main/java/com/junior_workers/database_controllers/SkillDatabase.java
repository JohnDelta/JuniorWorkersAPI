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
	
	private String tableName = "skill";
	
	public List<Skill> getAll() throws SQLException {
		
		List<Skill> skills = new ArrayList<Skill>();
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "SELECT * FROM " + this.tableName + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(query);
			
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
	
	public List<Skill> getByUser(User user) throws SQLException {
		
		List<Integer> userHasSkills = new ArrayList<Integer>();
		List<Skill> skills = new ArrayList<Skill>();
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "SELECT * FROM user_has_skill WHERE id_user=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, (int) user.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery(query);
			
            while(resultSet.next()) {
            	userHasSkills.add(resultSet.getInt("id_skill"));
            }
            
            for(int skillId : userHasSkills) {
            	query = "SELECT * FROM skill WHERE id_skill=?;";
            	preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, skillId);
                resultSet = preparedStatement.executeQuery(query);
                
                while(resultSet.next()) {
                	Skill skill = new Skill();
                	skill.setSkillId(resultSet.getLong("id_skill"));
                	skill.setTitle(resultSet.getString("title"));
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
	            preparedStatement.setInt(1, (int) skill.getSkillId());
	            preparedStatement.setInt(2, (int) user.getUserId());
	            preparedStatement.executeUpdate(query);	
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
	            
	            preparedStatement.setInt(1, (int) user.getUserId());
	            preparedStatement.setInt(2, (int) skill.getSkillId());
	            preparedStatement.executeUpdate(query);
			}
			
		} catch(Exception e) {
			System.out.println("Error in SkillDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return true;
	}

}


















