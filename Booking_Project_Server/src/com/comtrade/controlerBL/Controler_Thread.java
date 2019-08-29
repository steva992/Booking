package com.comtrade.controlerBL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.Type_Of_Data;
import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.user.User;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.message.Message;
import com.comtrade.threads.ClientThreadRequest;
import com.comtrade.transfer.TransferClass;

public class Controler_Thread {
	
	private static volatile Controler_Thread instance;
	private volatile List<ClientThreadRequest>listActiveThread;
	private volatile List<ClientThreadRequest>listAllThread;
	private volatile Map<String,ClientThreadRequest>mapActiveUsers;
	
	
	public void addInMapActiveThread(String username,ClientThreadRequest client) {
		mapActiveUsers.put(username, client);
	}


	public synchronized Map<String, ClientThreadRequest> getMapActiveUsers() {
		return mapActiveUsers;
	}


	public synchronized List<ClientThreadRequest> getListActiveThread() {
		return listActiveThread;
	}


	public synchronized List<ClientThreadRequest> getListAllThread() {
		return listAllThread;
	}


	public synchronized void add(ClientThreadRequest client) {
		listAllThread.add(client);
	}

	private Controler_Thread() {
		listActiveThread=new ArrayList<>();
		listAllThread=new ArrayList<>();
		mapActiveUsers=new HashMap<>();
	}
	
	public static Controler_Thread getInstance() {
		
		if(instance==null) {
			synchronized (Controler_Thread.class) {
				if(instance==null) {
					instance=new Controler_Thread();
				}
			}
		}
		return instance;
		
	}
	
	
	
	public synchronized void addNewThread(ClientThreadRequest clientThread) {
		
		listActiveThread.add(clientThread);
		
	}
	
	public synchronized void removeThread(ClientThreadRequest clientThread) {
		
		listActiveThread.add(clientThread);
		
	}
	
	private synchronized void sendRefactoring(Type_Of_Data data,ClientThreadRequest clientThread,TransferClass_Message message,Type_Of_Operation operation, GenericList<GeneralDomain> list) {
		
			TransferClass transferClass =new TransferClass();
			transferClass.setServer_Object_Response(list);
			transferClass.setType_Of_operation(operation);
			transferClass.setType_Of_Data(data);
			if(listActiveThread.size() == 0) {
				transferClass.setMessage(message.getValue());
				clientThread.send(transferClass);
			}else {
				for(ClientThreadRequest client : listActiveThread) {
					transferClass.setMessage(message.getValue());
					client.send(transferClass);
			}
		}
			
	}
	
	public synchronized int checkOnlineUser(User user) {
		
		if(mapActiveUsers.get(user.getUsername()) != null) {
			return 1;
		}
		return 0;
		
	}


	public  synchronized void removeThreadMap(String username) {
		mapActiveUsers.remove(username);
	}
	
	public synchronized void sendToClientForADD(Type_Of_Data data,ClientThreadRequest clientThread,TransferClass_Message message, GenericList<GeneralDomain> list) {
		sendRefactoring(data,clientThread,message,Type_Of_Operation.ADD,list);
	}
	
	public synchronized void sendToClientForUPDATE(Type_Of_Data data,ClientThreadRequest clientThread,TransferClass_Message message, GenericList<GeneralDomain> list) {
		sendRefactoring(data,clientThread,message,Type_Of_Operation.UPDATE,list);
		
	}

	public synchronized void sendToClientForDELETE(Type_Of_Data data,ClientThreadRequest clientThread,TransferClass_Message message, GenericList<GeneralDomain> list) {
		sendRefactoring(data,clientThread,message,Type_Of_Operation.DELETE,list);
		
	}
	
	

	public synchronized void sendToClientForUPDATEPICTURE(Type_Of_Data data,ClientThreadRequest clientThread,TransferClass_Message message, GenericList<GeneralDomain> list) {
		sendRefactoring(data,clientThread,message,Type_Of_Operation.UPDATE_PICTURE,list);
		
	}


	public void sendMESSAGE(Type_Of_Data data,ClientThreadRequest clientThread,TransferClass_Message message,GenericList<GeneralDomain>list) {
		sendRefactoring(data,clientThread,message,Type_Of_Operation.REMOVE_ONLINE_USER,list);
		
	}


	public void sendToCertainClient(GenericList<GeneralDomain> list,Type_Of_Operation type) {
		Message message=(Message) list.get(0);
		User user=(User) list.get(1);
		TransferClass transferClass=new TransferClass();
		transferClass.setServer_Object_Response(user);
		transferClass.setMessage(message.getMessage());
		transferClass.setType_Of_operation(type);
		transferClass.setType_Of_Data(Type_Of_Data.MESSAGE);
		ClientThreadRequest cetainClient=Controler_Thread.getInstance().getMapActiveUsers().get(message.getUser().getUsername());
		if(cetainClient != null) {
			cetainClient.send(transferClass);
		}
		
	}



	
	
}
