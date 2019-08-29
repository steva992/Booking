package com.comtrade.threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

import com.comtrade.cache.Cache;
import com.comtrade.constants.Server_Information;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.Type_Of_Data;
import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.controlerBL.ControlerBLCode;
import com.comtrade.controlerBL.ControlerBLDiscount;
import com.comtrade.controlerBL.ControlerBLMessage;
import com.comtrade.controlerBL.ControlerBLProperty;
import com.comtrade.controlerBL.ControlerBLRating;
import com.comtrade.controlerBL.ControlerBLReservation;
import com.comtrade.controlerBL.ControlerBLRoom;
import com.comtrade.controlerBL.ControlerBLUser;
import com.comtrade.controlerBL.Controler_Thread;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.transfer.TransferClass;

public class ClientThreadRequest extends Thread {
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
					
					try {
						
						TransferClass transferClass=(TransferClass) objectIS.readObject();
						processClientsRequest(transferClass);
					} catch (Exception e) {
							e.printStackTrace();
							break;
					}
					
				}catch(IOException e1) {
					Controler_Thread.getInstance().removeThread(this);
				}
		}		
	}

	private void processClientsRequest(TransferClass transferClass) throws Exception{
		Controler_Thread.getInstance().add(this);
		TransferClass transferClass2=new TransferClass();
		switch(transferClass.getType_Of_Data()) {
		case USER:
			
			ControlerBLUser.getInstance().CheckTheOperation(transferClass,this);	
			
			break;
		case PROPERTY:
			
			ControlerBLProperty.getInstance().CheckTheOperation(transferClass,this);
			
			break;
		case ROOM:
			
			ControlerBLRoom.getInstance().CheckTheOperation(transferClass,this);
			
			break;
		case DISCOUNT:
			
			ControlerBLDiscount.getInstance().CheckTheOperation(transferClass,this);
			
			break;
		case RESERVATION:
			
			ControlerBLReservation.getInstance().CheckTheOperation(transferClass,this);
			
			break;	
		case RATING:
			
			ControlerBLRating.getInstance().CheckTheOperation(transferClass,this);
			
			break;
		case CODE:
			
			ControlerBLCode.getInstance().CheckTheOperation(transferClass,this);
			
			break;		
		case MESSAGE:
			
			ControlerBLMessage.getInstance().CheckTheOperation(transferClass,this);
			
			break;	
		default:
			break;
		}
		
	}

	public void send(TransferClass transferClass2){
		try {
			ObjectOutputStream objectOS=new ObjectOutputStream(socket.getOutputStream());
			objectOS.writeObject(transferClass2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void sendRefactoring(TransferClass transferClass,Type_Of_Data data) {
		transferClass.setType_Of_Data(data);
		send(transferClass);
	}
	
	
	
	
}
