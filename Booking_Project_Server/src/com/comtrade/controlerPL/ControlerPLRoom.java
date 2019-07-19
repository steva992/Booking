package com.comtrade.controlerPL;

import com.comtrade.constants.TransferClass_Message;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.doman.room.Room;
import com.comtrade.doman.room.Room_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;
import com.comtrade.so.property.EntertPropertySO;
import com.comtrade.so.room.EntertRoomSO;
import com.comtrade.so.room.UpdateRoomSO;
import com.comtrade.transfer.TransferClass;

public class ControlerPLRoom {
private static volatile ControlerPLRoom instance;
	
	private ControlerPLRoom() {
		
	}
	
	public static ControlerPLRoom getInstance() {
		if(instance==null) {
			synchronized (ControlerPLProperty.class) {
				if(instance==null) {
					instance=new ControlerPLRoom();
				}
			}
		}
		return instance;
		
	}

	public TransferClass CheckTheOperation(TransferClass transferClass) {
		TransferClass transferClass2=new TransferClass();
		Room room=new Room();
		Room_Info room_Info=new Room_Info();
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		switch(transferClass.getType_Of_operation()) {
			case REGISTRATION_ROOM:
				list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				transferClass2=enterRoom(list);
				break;
			case UPDATE_ROOM:
				list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				transferClass2=UpdateRoom(list);
				break;	
				default:
					break;
		}
		return transferClass2;
	}

	private TransferClass UpdateRoom(GenericList<GeneralDomain> list) {
		TransferClass transferClass=new TransferClass();
		GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new UpdateRoomSO();
		try {
			generalSO.runSO(list);
			transferClass.setMessage(TransferClass_Message.SUCCESSFUL_CHANGE.getValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transferClass;
	}

	private TransferClass enterRoom(GenericList<GeneralDomain> list){
		TransferClass transferClass=new TransferClass();
		GenericList<GeneralDomain>list1=list;
		GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new EntertRoomSO();
		try {
			generalSO.runSO(list);
			transferClass.setMessage(TransferClass_Message.SUCCESSFUL_REGISTRATION.getValue());
			transferClass.setServer_Object_Response(list);
		} catch (Exception e) {
			transferClass.setMessage(TransferClass_Message.EXCIST_ROOM.getValue());
			e.printStackTrace();
		}
		return transferClass;
	}
	
	
}
