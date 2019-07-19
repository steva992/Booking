package com.comtrade.constants;

import java.io.File;

public enum PicturesURL {
	
	
	PICTURE_FOLDER("/picture"),
	PROFILE_PICTURE_DEFAULT("/Default"),
	PROFILE_PICTURE_DEFAULT_CRYING("/Default/Crying.jpg"),
	
	PROFILE_PICTURE_HOTELS("/Property"),
	PROFILE_PICTURE_USERS("/Users"),
	PICTURE_FOR_SERVER("/Server"),
	PROFILE_PICTURE_USER_COUNTRYES("/CountryesFlag");
	
	
	
	private String value;

	private PicturesURL(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
