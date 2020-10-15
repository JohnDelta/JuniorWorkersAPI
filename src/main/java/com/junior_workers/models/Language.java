package com.junior_workers.models;

import jakarta.json.bind.annotation.JsonbProperty;

public class Language {

	@JsonbProperty("id_language")
	private long languageId;
	private String title;
	@JsonbProperty("language_level")
	private LanguageLevel languageLevel;
	
	public long getLanguageId() {
		return languageId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setLanguageId(long languageId) {
		this.languageId = languageId;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public LanguageLevel getLanguageLevel() {
		return languageLevel;
	}

	public void setLanguageLevel(LanguageLevel languageLevel) {
		this.languageLevel = languageLevel;
	}
	
}
