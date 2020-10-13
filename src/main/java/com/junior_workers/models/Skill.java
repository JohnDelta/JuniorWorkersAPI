package com.junior_workers.models;

import jakarta.json.bind.annotation.JsonbProperty;

public class Skill {

	@JsonbProperty("id_skill")
	private long skillId;
	private String title;
	
	public long getSkillId() {
		return skillId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
}
