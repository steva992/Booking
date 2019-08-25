package com.comtrade.constants;

public enum Panel_Dimension {
	
	X(0),
	Y(0),
	WIDTH(1250),
	HEIGHT(750);
	
	private int value;

	private Panel_Dimension(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	
}
