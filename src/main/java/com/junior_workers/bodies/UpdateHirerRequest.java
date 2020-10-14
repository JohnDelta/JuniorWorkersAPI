package com.junior_workers.bodies;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.junior_workers.models.JobPost;
import com.junior_workers.models.User;

import jakarta.json.bind.annotation.JsonbProperty;

@JsonInclude(Include.NON_NULL)
public class UpdateHirerRequest {

	private String jwt;
	private User user;
	@JsonbProperty("job_post")
	private List<JobPost> jobPosts;
	
	public String getJwt() {
		return jwt;
	}
	
	public User getUser() {
		return user;
	}
	
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
