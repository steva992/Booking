package com.comtrade.constants;

public enum PicturesURL {
	PROFILE_PICTURE_DEFAULT("C:/Users/stevd/OneDrive/Desktop/Final_Project/Booking/Booking_Project_Server/picture/default1"),
	PROFILE_PICTURE_HOTELS("C:/Users/stevd/OneDrive/Desktop/Final_Project/Booking/Booking_Project_Server/picture/hotels"),
	PROFILE_PICTURE_USERS("C:/Users/stevd/OneDrive/Desktop/Final_Project/Booking/Booking_Project_Server/picture/users");
	
	
	
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
