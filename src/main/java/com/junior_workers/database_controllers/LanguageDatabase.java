package com.junior_workers.database_controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.junior_workers.models.LanguageLevel;
import com.junior_workers.models.Language;
import com.junior_workers.models.User;

public class LanguageDatabase {
	
	public List<Language> getLanguageAll() throws SQLException {
		
		List<Language> languages = new ArrayList<Language>();
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "SELECT * FROM language";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
			
            while(resultSet.next()) {
            	Language language = new Language();
            	language.setLanguageId(resultSet.getLong("id_language"));
            	language.setTitle(resultSet.getString("title"));
            	languages.add(language);
            }
            
			return languages;
			
		} catch(Exception e) {
			System.out.println("Error in LanguageDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return null;
	}
	
	public List<LanguageLevel> getLanguageLevelAll() throws SQLException {
		
		List<LanguageLevel> languageLevels = new ArrayList<LanguageLevel>();
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "SELECT * FROM language_level";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
			
            while(resultSet.next()) {
            	LanguageLevel languageLevel = new LanguageLevel();
            	languageLevel.setLanguageLevelId(resultSet.getLong("id_language_level"));
            	languageLevel.setTitle(resultSet.getString("title"));
            	languageLevels.add(languageLevel);
            }
            
			return languageLevels;
			
		} catch(Exception e) {
			System.out.println("Error in LanguageDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return null;
	}
	
	public Language getLanguageById(long languageId) throws SQLException {
		
		Language language = null;
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "SELECT * FROM language WHERE id_language=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, languageId);
            ResultSet resultSet = preparedStatement.executeQuery();
			
            if(resultSet.next()) {
            	language = new Language();
            	language.setLanguageId(resultSet.getLong("id_language"));
            	language.setTitle(resultSet.getString("title"));
            }
            
		} catch(Exception e) {
			System.out.println("Error in LanguageDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return language;
	}
	
	public LanguageLevel getLanguageLevelById(long languageLevelId) throws SQLException {
		
		LanguageLevel languageLevel = null;
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "SELECT * FROM language_level WHERE id_language_level=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, languageLevelId);
            ResultSet resultSet = preparedStatement.executeQuery();
			
            if(resultSet.next()) {
            	languageLevel = new LanguageLevel();
            	languageLevel.setLanguageLevelId(resultSet.getLong("id_language_level"));
            	languageLevel.setTitle(resultSet.getString("title"));
            }
            
		} catch(Exception e) {
			System.out.println("Error in LanguageDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return languageLevel;
	}
	
	public List<Language> getLanguageAndLevelByUser(User user) throws SQLException {
		
		List<Integer> userHasLanguages = new ArrayList<Integer>();
		List<Integer> userHasLanguageLevels = new ArrayList<Integer>();
		List<Language> languages = new ArrayList<Language>();
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();

			String query = "SELECT * FROM user_has_language WHERE id_user=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, user.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
			
            while(resultSet.next()) {
            	userHasLanguages.add(resultSet.getInt("id_language"));
            	userHasLanguageLevels.add(resultSet.getInt("id_language_level"));
            }
            
            int index = 0;
            for(int languageId : userHasLanguages) {
            	int languageLevelId = userHasLanguageLevels.get(index);
            	
            	Language language = getLanguageById(languageId);
            	language.setLanguageLevel(getLanguageLevelById(languageLevelId));
            	
            	if(language != null) {
            		languages.add(language);
            	}
            	index++;
            }
            
			return languages;
			
		} catch(Exception e) {
			System.out.println("Error in LanguageDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return null;
	}
	
	public boolean deleteByUser(User user) throws SQLException {
		
		List<Language> languages = getLanguageAndLevelByUser(user);
		
		if(languages.isEmpty()) {
			return false;
		}
		
		Connection connection = null;
		try{
			connection = new Database().getConnection();
			String query = "";
			PreparedStatement preparedStatement = null;
			
			for(Language language: languages) {
				query = "DELETE FROM user_has_language "
						+ "WHERE id_language=? AND id_user=? AND id_language_level=?";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setLong(1, language.getLanguageId());
	            preparedStatement.setLong(2, user.getUserId());
	            preparedStatement.setLong(3, language.getLanguageLevel().getLanguageLevelId());
	            preparedStatement.executeUpdate();	
			}
            
		} catch(Exception e) {
			System.out.println("Error in LanguageDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
				
		return true;
	}
	
	public boolean addToUser(User user, List<Language> languages) throws SQLException {

		Connection connection = null;
		try{
			connection = new Database().getConnection();
			String query = "";
			PreparedStatement preparedStatement = null;
			
			for(Language language: languages) {
				query = "INSERT INTO user_has_language (id_user, id_language, id_language_level)"
						+ " VALUES(?, ?, ?);";
	            preparedStatement = connection.prepareStatement(query);
	            
	            preparedStatement.setLong(1, user.getUserId());
	            preparedStatement.setLong(2, language.getLanguageId());
	            preparedStatement.setLong(3, language.getLanguageLevel().getLanguageLevelId());
	            preparedStatement.executeUpdate();
			}
			
		} catch(Exception e) {
			System.out.println("Error in LanguageDatabase.java | Exception message: " + e.getMessage());
		} finally {
			connection.close();
		}
		
		return true;
	}

}
