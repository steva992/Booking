package com.comtrade.threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.comtrade.constants.Server_Information;
import com.comtrade.constants.Type_Of_Data;
import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.controlerPL.ControlerPLUser;
import com.comtrade.domain.User_Additional_Features;
import com.comtrade.transfer.TransferClass;

public class ClientThread extends Thread {
	private Socket socket;

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		while(true) {
			try {
				ObjectInputStream objectIS=new ObjectInputStream(socket.getInputStream());
				TransferClass transferClass=(TransferClass) objectIS.readObject();
				processClientsRequest(transferClass);
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	private void processClientsRequest(TransferClass transferClass){
		TransferClass transferClass2=new TransferClass();
		switch(transferClass.getType_Of_Data()) {
		case Type_Of_Data.USER:
			transferClass2=ControlerPLUser.getInstance().CheckTheOperation(transferClass);
			send(transferClass2);
			break;
		default:
			break;
		}
		
	}

	private void send(TransferClass transferClass2){
		try {
			ObjectOutputStream objectOS=new ObjectOutputStream(socket.getOutputStream());
			objectOS.writeObject(transferClass2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
