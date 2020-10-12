package com.junior_workers.bodies;

import java.util.List;

import com.junior_workers.models.*;

public class ModelsResponse {

	private List<Education> allEducation;
	private List<EducationLevel> educationLevels;
	private List<Language> languages;
	private List<LanguageLevel> languageLevels;
	private List<Skill> skills;
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
