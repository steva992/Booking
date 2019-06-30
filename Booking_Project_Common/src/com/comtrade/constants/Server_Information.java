package com.comtrade.constants;

public enum Server_Information {
	IP_ADRESA("127.0.0.1"),PORT(9000);
	
	private String text;
	private int value;
	
	private Server_Information(int value) {
		this.value = value;
	}

	private Server_Information(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public int getValue() {
		return value;
	}
	
	
}
