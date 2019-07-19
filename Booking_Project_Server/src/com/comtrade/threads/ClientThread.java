package com.comtrade.threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

import com.comtrade.constants.Server_Information;
import com.comtrade.constants.Type_Of_Data;
import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.controlerPL.ControlerPLDiscount;
import com.comtrade.controlerPL.ControlerPLProperty;
import com.comtrade.controlerPL.ControlerPLRoom;
import com.comtrade.controlerPL.ControlerPLUser;
import com.comtrade.domain.user.User_Info;
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
				try {
					processClientsRequest(transferClass);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	private void processClientsRequest(TransferClass transferClass) throws Exception{
		TransferClass transferClass2=new TransferClass();
		switch(transferClass.getType_Of_Data()) {
		case USER:
			transferClass2=ControlerPLUser.getInstance().CheckTheOperation(transferClass);
			send(transferClass2);
			break;
		case PROPERTY:
			transferClass2=ControlerPLProperty.getInstance().CheckTheOperation(transferClass);
			send(transferClass2);
			break;
		case ROOM:
			transferClass2=ControlerPLRoom.getInstance().CheckTheOperation(transferClass);
			send(transferClass2);
			break;
		case DISCOUNT:
			transferClass2=ControlerPLDiscount.getInstance().CheckTheOperation(transferClass);
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
