package com.comtrade.genericClasses;

public interface Generic <T extends Generic>{
	public default T getParent() {
		return null;
	}
}
