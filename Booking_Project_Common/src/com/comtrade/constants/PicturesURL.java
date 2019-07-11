package com.comtrade.constants;

public enum PicturesURL {
	
	PROJECT_PATH("C:/Users/stevd/OneDrive/Desktop/Final_Project/Booking/Booking_Project_Server"),
	
	
	
	PROFILE_PICTURE_DEFAULT("/picture/Default"),
	PROFILE_PICTURE_DEFAULT_CRYING("/picture/Default/Crying.jpg"),
	
	PROFILE_PICTURE_HOTELS("/picture/Property"),
	PROFILE_PICTURE_USERS("/picture/Users"),
	PICTURE_FOR_SERVER("/picture/Server");
	
	
	
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
