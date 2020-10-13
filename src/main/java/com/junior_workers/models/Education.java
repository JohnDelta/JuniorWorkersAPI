package com.junior_workers.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.json.bind.annotation.JsonbProperty;

public class Education {

	@JsonbProperty("id_education")
	private long educationId;
	private String title;
	@JsonIgnore
	private EducationLevel educationLevel;
	
	public long getEducationId() {
		return educationId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setEducationId(long educationId) {
		this.educationId = educationId;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public EducationLevel getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}
	
}
