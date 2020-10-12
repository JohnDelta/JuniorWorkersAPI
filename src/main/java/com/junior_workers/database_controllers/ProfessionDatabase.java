package com.junior_workers.database_controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.junior_workers.models.Profession;

public class ProfessionDatabase {
	
	public List<Profession> getAll() throws SQLException {
		
		List<Profession> professions = new ArrayList<Profession>();
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "SELECT * FROM profession";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
			
            while(resultSet.next()) {
            	Profession profession = new Profession();
            	profession.setProfessionId(resultSet.getLong("id_profession"));
            	profession.setTitle(resultSet.getString("title"));
            	professions.add(profession);
            }
            
			return professions;
			
		} catch(Exception e) {
			System.out.println("Error in ProfessionDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return null;
	}
	
	public Profession getById(long professionId) throws SQLException {
		
		Profession profession = null;
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "SELECT * FROM profession WHERE id_profession=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, professionId);
            ResultSet resultSet = preparedStatement.executeQuery();
			
            if(resultSet.next()) {
            	profession = new Profession();
            	profession.setProfessionId(resultSet.getLong("id_profession"));
            	profession.setTitle(resultSet.getString("title"));
            }
            
		} catch(Exception e) {
			System.out.println("Error in ProfessionDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return profession;
	}
	
}
