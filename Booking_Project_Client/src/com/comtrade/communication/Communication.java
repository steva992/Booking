package com.comtrade.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.comtrade.constants.Server_Information;
import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.transfer.TransferClass;

public class Communication {
	private static Communication instance;
	private Socket socket;
	
	private Communication() {
		try {
			socket=new Socket(Server_Information.IP_ADRESA.getText(),Server_Information.PORT.getValue());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Communication getInstance() {
		if(instance==null) {
			instance=new Communication();
		}
		return instance;
		
	}
	
	public void send(TransferClass transferClass) {
		try {
			ObjectOutputStream objectOS=new ObjectOutputStream(socket.getOutputStream());
			objectOS.writeObject(transferClass);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public TransferClass read() throws IOException, ClassNotFoundException {
		ObjectInputStream objectIS=new ObjectInputStream(socket.getInputStream());
		return (TransferClass) objectIS.readObject();
		
	}
	
}
