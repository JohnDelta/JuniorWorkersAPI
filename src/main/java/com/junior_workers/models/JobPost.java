package com.junior_workers.models;

import jakarta.json.bind.annotation.JsonbProperty;

public class JobPost {

	@JsonbProperty("id_job_post")
	private long jobPostId;
	private String description;
	private String title;
	private Profession profession;
	
	public long getJobPostId() {
		return jobPostId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Profession getProfession() {
		return profession;
	}
	
	public void setJobPostId(long jobPostId) {
		this.jobPostId = jobPostId;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setProfession(Profession profession) {
		this.profession = profession;
	}
	
}
