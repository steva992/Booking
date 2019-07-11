package com.comtrade.constants;

public enum Threads_Constant {
	SLEEP(1000);
	
	private int value;

	private Threads_Constant(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	
}
