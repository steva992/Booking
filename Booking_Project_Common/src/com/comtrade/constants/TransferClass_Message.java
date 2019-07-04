package com.comtrade.constants;

public enum TransferClass_Message {
	SUCCESSFUL_REGISTRATION("SUCCESFULL REGISTRATION"),
	SUCCESSFUL_CHANGE("SUCCESFULL CHANGE"),
	SUCCESSFUL_LOGIN("SUCCESFULL LOGIN"),
	WRONG_USERNAME_OR_PASSWORD("WRONG USERNAME OR PASSWORD"),
	EXCIST_USERNAME("USERNAME ALLREADY EXCIST"),
	EXCIST_PROPERTY("PROPERTY ALLREADY EXCIST"),
	ALL_FIELDS_FILL("ALL FIELDS MUST BE FILL"),
	INCORECT_ENTER_DATA("INCORRECT ENTER DATA"),
	INCORECT_ENTER_PASSWORD("INCORRECT ENTER PASSWORD");
	
	private String value;

	private TransferClass_Message(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
