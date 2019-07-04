package com.comtrade.constants;

public enum PicturesURL {
	PROFILE_PICTURE("C/Users/stevd/OneDrive/Desktop/Final_Project/Booking/Booking_Project_Server/picture/");
	
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
