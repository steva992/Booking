package com.comtrade.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.swing.JTextArea;

public class ReceivingMessageThread extends Thread {
	private BufferedReader bufferedReader;
	private JTextArea textArea;
	
	public ReceivingMessageThread(BufferedReader bufferedReader, JTextArea textArea) {
		super();
		this.bufferedReader = bufferedReader;
		this.textArea = textArea;
		start();
	}
	
	public void run() {
		String message;
		while(true) {
			try {
				message=bufferedReader.readLine();
				if(message !=null && !message.isEmpty()) {
					textArea.append(message);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
