package com.junior_workers.bodies;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.junior_workers.models.*;

import jakarta.json.bind.annotation.JsonbProperty;

@JsonInclude(Include.NON_NULL)
public class UpdateCandidateRequest {
	
	private String jwt;
	private User user;
	@JsonbProperty("skill")
	private List<Skill> skills;
	@JsonbProperty("language")
	private List<Language> languages;
	@JsonbProperty("education")
	private List<Education> allEducation;
	@JsonbProperty("experience")
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
	
	public List<Skill> getSkills() {
		return skills;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public List<Education> getAllEducation() {
		return allEducation;
	}

	public void setAllEducation(List<Education> allEducation) {
		this.allEducation = allEducation;
	}

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}
	
}