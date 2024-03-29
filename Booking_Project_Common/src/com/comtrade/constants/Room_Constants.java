package com.comtrade.constants;

public enum Room_Constants {
	
	ID_ROOM("ID ROOM"),
	ROOM_TYPE("ROOM TYPE"),
	PRICE_PER_NIGHT("PRICE NIGHT"),
	NUMBER_OF_BEED("NUMBER BEDS"),
	
	ORDINARY_ROOM("Ordinary"),
	LUXURY_ROOM("Luxury"),
	APARTMENT("Apartment"), 
	OLD_PRICE("OLD PRICE"),
	ACTION_PRICE("ACTION"), 
	ROOM_CODE("CODE");
	
	private String value;

	private Room_Constants(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
