package com.junior_workers.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.json.bind.annotation.JsonbProperty;

public class User {

	@JsonbProperty("id_user")
	private long userId;
	private String email;
	@JsonIgnore
	private String password;
	private String firstname;
	private String lastname;
	private int availability = 0;
	private String title = "";
	private String bio = "";
	private String role = "candidate";
	@JsonbProperty("image_path")
	private String imagePath = "";
	@JsonbProperty("video_path")
	private String videoPath = "";
	@JsonbProperty("resume_path")
	private String resumePath = "";
	
	public long getUserId() {
		return userId;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public int getAvailability() {
		return availability;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getBio() {
		return bio;
	}
	
	public String getRole() {
		return role;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public String getVideoPath() {
		return videoPath;
	}
	
	public String getResumePath() {
		return resumePath;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public void setAvailability(int availability) {
		this.availability = availability;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	
	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}
	
}
