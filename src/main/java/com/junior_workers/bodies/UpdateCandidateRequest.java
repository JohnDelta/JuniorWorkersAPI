package com.junior_workers.bodies;

import java.util.List;

import com.junior_workers.models.*;

import jakarta.json.bind.annotation.JsonbProperty;

public class UpdateCandidateRequest {
	
	private String jwt;
	private User user;
	private List<Skill> skills;
	private List<Language> languages;
	private List<Education> allEducation;
	private List<Experience> experiences;
	
	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	public User getUser() {
		return user;
	}
	
	@JsonbProperty("skill")
	public List<Skill> getSkills() {
		return skills;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	@JsonbProperty("language")
	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	@JsonbProperty("education")
	public List<Education> getAllEducation() {
		return allEducation;
	}

	public void setAllEducation(List<Education> allEducation) {
		this.allEducation = allEducation;
	}

	@JsonbProperty("experience")
	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}
	
}