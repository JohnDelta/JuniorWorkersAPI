package com.junior_workers.bodies;

public class UserUpdateRequest {
	
	private String jwt = "";
	private String firstname = "";
	private String lastname = "";
	private int availability = -1;
	private String title = "";
	private String bio = "";
	private String imagePath = "";
	private String videoPath = "";
	private String resumePath = "";
	
	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
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
	
	public String getImagePath() {
		return imagePath;
	}
	
	public String getVideoPath() {
		return videoPath;
	}
	
	public String getResumePath() {
		return resumePath;
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