package com.comtrade.threads;

import java.io.BufferedReader;
import java.io.PrintWriter;

import com.comtrade.domain.message.Message;

public class SendingMessageThread extends Thread{
	private PrintWriter printWriter;
	private Message message;
	
	public SendingMessageThread(PrintWriter printWriter, Message message) {
		super();
		this.printWriter = printWriter;
		this.message = message;
		start();
	}
	
	public void run() {
		while(true) {
			printWriter.println(message.getPoruka());
		}
	}
}
