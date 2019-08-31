package com.comtrade.threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.comtrade.cache.Cache;
import com.comtrade.constants.Server_Information;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.controlerBL.ControlerBLServer;

public class ServerThread extends Thread {
	private JTextArea textArea;
	
	public ServerThread(JTextArea textArea) {
		super();
		this.textArea = textArea;
	}

	public void run() {
		try {
			strartServer();
		} catch (Exception e) {
			System.exit(0);
		}
	}

	private void strartServer() throws Exception {
		try {
			
			ServerSocket serverSocket=new ServerSocket(Server_Information.PORT.getValue());
			ControlerBLServer.getInstance().entrerMessageOnTextArea("[ "+LocalDate.now()+" "+LocalTime.now()+" ] : Server is connect \n");
			
			while(true) {
				
				Socket socket=serverSocket.accept();
				ClientThreadRequest clientThread=new ClientThreadRequest();
				clientThread.setSocket(socket);
				clientThread.start();
				DatabaseThread database=new DatabaseThread();
				database.start();
				
			}
		} catch (IOException e) {
			
			JOptionPane.showMessageDialog(null,TransferClass_Message.SERVER_ALLREADY_STARTED.getValue());
			
		}
		
	}
}
