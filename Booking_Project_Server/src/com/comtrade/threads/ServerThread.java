package com.comtrade.threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.swing.JTextArea;

import com.comtrade.constants.Server_Information;
import com.comtrade.controlerPL.ControlerPLServer;

public class ServerThread extends Thread {
	private JTextArea textArea;
	
	public ServerThread(JTextArea textArea) {
		super();
		this.textArea = textArea;
	}

	public void run() {
		strartServer();
	}

	private void strartServer() {
		try {
			ServerSocket serverSocket=new ServerSocket(Server_Information.PORT.getValue());
			ControlerPLServer.getInstance().entrerMessageOnTextArea("[ "+LocalDate.now()+" "+LocalTime.now()+" ] : Server is connect ");
			while(true) {
				Socket socket=serverSocket.accept();
				ClientThread clientThread=new ClientThread();
				clientThread.setSocket(socket);
				clientThread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
