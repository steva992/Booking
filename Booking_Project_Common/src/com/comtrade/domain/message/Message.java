package com.comtrade.domain.message;

public class Message {
	private String message;
	
	public synchronized String getPoruka() {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return message;
		
	}
	
	public synchronized void setPoruka(String message) {
		this.message=message;
		notify();
	}
}
