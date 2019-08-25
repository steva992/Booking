package com.comtrade.constants;

public enum CountryesTxt {
	
	ALL_COUNTRIES_AND_CITIES("/Countryes.txt"),	
	ALL_COUNTRIES_CALL("/CountryesCall.txt"),
	ALL_CODES("/Codes.txt");
	
	private String value;

	private CountryesTxt(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
