package com.comtrade.constants;

public enum Regular_Expression {
	
	ONLY_TEXT_VALUES("^([a-zA-Z]+|[A-za-z]\\s)$"),
	ONLY_NUMBER_VALUES("^[0-9]{1,4}$"),
	EMAIL("^[a-z0-9._+-]+@[a-z]+\\.(com|rs|co.rs|net)$"),
	PHONE_NUMBER("^[+][0-9]+$"),
	ADRESS("^[[A-Z,0-9]{1,2}[a-z]+\\s]+"),
	USRERNAME("^[A-z][a-zA-Z0-9]+$"),
	LATITUDE_LONGITUDE("[0-8 +-][0-9 +-]\\.[0-9]{4}");
	
	private String value;

	private Regular_Expression(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	
}
