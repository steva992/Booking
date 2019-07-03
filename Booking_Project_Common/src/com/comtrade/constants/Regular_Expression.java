package com.comtrade.constants;

public enum Regular_Expression {
	
	ONLY_TEXT_VALUES("^[a-zA-Z]+$"),
	ONLY_NUMBER_VALUES("^[0-9]{1,4}$"),
	EMAIL("^[a-z0-9._+-]+@[a-z]+\\.(com|rs|co.rs|net)$"),
	PHONE_NUMBER("^[0][0-9]+$"),
	ADRESS("^[A-Za-z]+[\\s[A-Z][a-z]]+$"),
	USRERNAME("^[a-z][a-zA-Z0-9]+$");
	
	private String value;

	private Regular_Expression(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	
}
