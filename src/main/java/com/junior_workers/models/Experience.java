package com.junior_workers.models;

import jakarta.json.bind.annotation.JsonbProperty;

public class Experience {

	@JsonbProperty("id_experience")
	private long experienceId;
	private String company;
	private String date;
	private User user;
	private Profession profession;
	
	public long getExperienceId() {
		return experienceId;
	}
	
	public String getCompany() {
		return company;
	}
	
	public String getDate() {
		return date;
	}
	
	public User getUser() {
		return user;
	}
	
	public Profession getProfession() {
		return profession;
	}
	
	public void setExperienceId(long experienceId) {
		this.experienceId = experienceId;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setProfession(Profession profession) {
		this.profession = profession;
	}
	
}
