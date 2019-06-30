package com.comtrade.threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

import com.comtrade.constants.Server_Information;

public class ServerThread extends Thread {
	

	public void run() {
		strartServer();
	}

	private void strartServer() {
		try {
			ServerSocket serverSocket=new ServerSocket(Server_Information.PORT.getValue());
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
