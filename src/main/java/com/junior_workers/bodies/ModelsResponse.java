package com.junior_workers.bodies;

import java.util.List;

import com.junior_workers.models.*;

import jakarta.json.bind.annotation.JsonbProperty;

public class ModelsResponse {

	@JsonbProperty("education")
	private List<Education> allEducation;
	@JsonbProperty("education_level")
	private List<EducationLevel> educationLevels;
	@JsonbProperty("language")
	private List<Language> languages;
	@JsonbProperty("language_level")
	private List<LanguageLevel> languageLevels;
	@JsonbProperty("skill")
	private List<Skill> skills;
	@JsonbProperty("profession")
	private List<Profession> professions;
	
	public List<Education> getAllEducation() {
		return allEducation;
	}
	
	public List<EducationLevel> getEducationLevels() {
		return educationLevels;
	}
	
	public List<Language> getLanguages() {
		return languages;
	}
	
	public List<LanguageLevel> getLanguageLevels() {
		return languageLevels;
	}
	
	public List<Skill> getSkills() {
		return skills;
	}
	
	public List<Profession> getProfessions() {
		return professions;
	}
	
	public void setAllEducation(List<Education> allEducation) {
		this.allEducation = allEducation;
	}
	
	public void setEducationLevels(List<EducationLevel> educationLevels) {
		this.educationLevels = educationLevels;
	}
	
	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}
	
	public void setLanguageLevels(List<LanguageLevel> languageLevels) {
		this.languageLevels = languageLevels;
	}
	
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	
	public void setProfessions(List<Profession> professions) {
		this.professions = professions;
	}
	
}
