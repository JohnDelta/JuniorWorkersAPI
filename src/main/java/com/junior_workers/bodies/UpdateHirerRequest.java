package com.junior_workers.bodies;

import java.util.List;

import com.junior_workers.models.JobPost;
import com.junior_workers.models.User;

import jakarta.json.bind.annotation.JsonbProperty;

public class UpdateHirerRequest {

	private String jwt;
	private User user;
	private List<JobPost> jobPosts;
	
	public String getJwt() {
		return jwt;
	}
	
	public User getUser() {
		return user;
	}
	
	@JsonbProperty("job_post")
	public List<JobPost> getJobPosts() {
		return jobPosts;
	}
	
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setJobPosts(List<JobPost> jobPosts) {
		this.jobPosts = jobPosts;
	}
	
}
