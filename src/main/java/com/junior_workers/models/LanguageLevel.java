package com.junior_workers.models;

import jakarta.json.bind.annotation.JsonbProperty;

public class LanguageLevel {

	@JsonbProperty("id_language_level")
	private long languageLevelId;
	private String title;
	
	public long getLanguageLevelId() {
		return languageLevelId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setLanguageLevelId(long languageLevelId) {
		this.languageLevelId = languageLevelId;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
}
