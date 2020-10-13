package com.junior_workers.models;

import jakarta.json.bind.annotation.JsonbProperty;

public class Profession {

	@JsonbProperty("id_profession")
	private long professionId;
	private String title;
	
	public long getProfessionId() {
		return professionId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setProfessionId(long professionId) {
		this.professionId = professionId;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
}
