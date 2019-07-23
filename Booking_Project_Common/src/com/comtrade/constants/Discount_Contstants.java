package com.comtrade.constants;

public enum Discount_Contstants {
	ID_DISCOUNT("ID DISCOUNT"),FROM_DATE("FROM DATE"),TO_DATE("TO DATE"),AMOUNT_OF_DISCOUNT("AMOUNT");
	
	private String value;

	private Discount_Contstants(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
