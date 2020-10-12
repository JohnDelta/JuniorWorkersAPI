package com.junior_workers.bodies;

import java.util.List;

import com.junior_workers.models.JobPost;
import com.junior_workers.models.User;

public class HirerResponse {
	
	private User user;
	private List<JobPost> jobPosts;
	
	public User getUser() {
		return user;
	}
	
	public List<JobPost> getJobPosts() {
		return jobPosts;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setJobPosts(List<JobPost> jobPosts) {
		this.jobPosts = jobPosts;
	}

}
