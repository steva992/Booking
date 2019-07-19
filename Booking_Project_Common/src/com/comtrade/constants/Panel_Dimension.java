package com.comtrade.constants;

public enum Panel_Dimension {
	X(100),Y(100),WIDTH(1000),HEIGHT(800);
	
	private int value;

	private Panel_Dimension(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	
}
