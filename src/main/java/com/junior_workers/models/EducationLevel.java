package com.junior_workers.models;

import jakarta.json.bind.annotation.JsonbProperty;

public class EducationLevel {

	@JsonbProperty("id_education_level")
	private long educationLevelId;
	private String title;
	
	public long getEducationLevelId() {
		return educationLevelId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setEducationLevelId(long educationLevelId) {
		this.educationLevelId = educationLevelId;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
}
