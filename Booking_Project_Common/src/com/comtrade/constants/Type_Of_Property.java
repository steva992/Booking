package com.comtrade.constants;

public enum Type_Of_Property {
	
	HOTEL("Hotel"),
	MOTEL("Motel"),
	HOSTEL("Hostel"),
	VILLA("Villa");
	
	private String value;

	private Type_Of_Property(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
