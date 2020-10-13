package com.junior_workers.bodies;

import jakarta.json.bind.annotation.JsonbProperty;

public class SearchBody {
	
	// for hirer and candidate
	private String email;
	private String firstname;
	private String lastname;
	private String title;
	private String role;
	@JsonbProperty("image_path")
	private String imagePath;
	
	//for hirer
	@JsonbProperty("job_title")
	private String jobTitle;
	private String description;
	@JsonbProperty("id_profession")
	private long professionId;
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getRole() {
		return role;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}
	
	public String getDescription() {
		return description;
	}
	
	public long getProfessionId() {
		return professionId;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setProfessionId(long professionId) {
		this.professionId = professionId;
	}

}
