package com.comtrade.constants;

public enum Type_Of_User_Card {

	MASTER("MASTER"),
	DINA("DINA"),
	VISE("VISA");
	
	
	
	private Type_Of_User_Card(String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
