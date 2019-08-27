package com.comtrade.constants;

public enum Reservation_Constant {

	CHECK_IN("CHECK IN"),
	CHECK_OUT("CHECK OUT"),
	NUMBER_ADULTS("ADULTS"),
	NUMBER_CHILDREN("CHILDREN"),
	NUMBER_NIGHTS("NIGHTS"),
	AMOUNT("AMOUNT"),
	USER("USER"),
	ROOM_CODE("ROOM CODE"),
	RESERVATION_CODE("CODE"),
	PROPERTY("PROPERTY"),
	ACTIVE("(IN)ACTIVE");
	
	private String value;

	

	private Reservation_Constant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
