package com.comtrade.controlerClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import com.comtrade.communication.Communication;
import com.comtrade.constants.Type_Of_Data;
import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.transfer.TransferClass;

public class ControlerUI {
	private static ControlerUI instance;
	private TransferClass transferClass=new TransferClass();
	
	private ControlerUI() {
		
	}
	
	public static ControlerUI getInstance() {
		if(instance==null) {
			instance=new ControlerUI();
		}
		return instance;
		
	}
	
	public void sendToServer(Type_Of_Operation operation,Type_Of_Data data,Object client) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(operation);
		transferClass.setType_Of_Data(data);
		transferClass.setClient_Object_Request(client);
		Communication.getInstance().send(transferClass);
	}

	
	
	
	


	
	

	
}
