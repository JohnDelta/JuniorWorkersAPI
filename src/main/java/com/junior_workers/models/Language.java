package com.junior_workers.models;

public class Language {

	private long languageId;
	private String title;
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
