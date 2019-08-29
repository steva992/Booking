package com.comtrade.controlerBL;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.message.Message;
import com.comtrade.so.GeneralSystemOperation;
import com.comtrade.so.message.DeleteFromChatSO;
import com.comtrade.so.message.SendMessageUserSO;
import com.comtrade.threads.ClientThreadRequest;
import com.comtrade.transfer.TransferClass;

public class ControlerBLMessage {
private static ControlerBLMessage instance;
	
	private ControlerBLMessage() {
		
	}
	
	public static ControlerBLMessage getInstance() {
		if(instance==null) {
			synchronized (ControlerBLMessage.class) {
				if(instance==null) {
					instance=new ControlerBLMessage();
				}
			}
		}
		return instance;
		
	}

	public void CheckTheOperation(TransferClass transferClass, ClientThreadRequest clientThreadRequest) {
		TransferClass transferClass2=new TransferClass();
		Message message=new Message();
		GenericList<GeneralDomain>list=new GenericList<>();
		switch(transferClass.getType_Of_operation()) {
			case SENDMESSAGE:
				list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				sendMessageToUser(list);
				break;
			case DELETE_FROM_ADMIN_CHAT:
				list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				deleteFromAdminChat(list);
				break;	
				
			default:
				break;
		}
	}

	private void deleteFromAdminChat(GenericList<GeneralDomain> list) {
		GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new DeleteFromChatSO();
		try {
			generalSO.runSO(list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void sendMessageToUser(GenericList<GeneralDomain> list) {
		GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new SendMessageUserSO();
		try {
			generalSO.runSO(list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
