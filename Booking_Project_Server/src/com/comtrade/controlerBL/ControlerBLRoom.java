package com.comtrade.controlerBL;

import java.util.List;

import com.comtrade.cache.Cache;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.Type_Of_Data;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Property;
import com.comtrade.doman.room.Room;
import com.comtrade.doman.room.Room_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;
import com.comtrade.so.room.DeleteRoomSO;
import com.comtrade.so.room.EnterRoomSO;
import com.comtrade.so.room.UpdateRoomSO;
import com.comtrade.threads.ClientThreadRequest;
import com.comtrade.transfer.TransferClass;

public class ControlerBLRoom {
	private static volatile ControlerBLRoom instance;
	
	private ControlerBLRoom() {
		
	}
	
	public static ControlerBLRoom getInstance() {
		if(instance==null) {
			synchronized (ControlerBLProperty.class) {
				if(instance==null) {
					instance=new ControlerBLRoom();
				}
			}
		}
		return instance;
		
	}

	public void CheckTheOperation(TransferClass transferClass, ClientThreadRequest clientThread) {
		TransferClass transferClass2=new TransferClass();
		Room room=new Room();
		Room_Info room_Info=new Room_Info();
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		switch(transferClass.getType_Of_operation()) {
			case REGISTRATION_ROOM:
				list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				enterRoom(list,clientThread);
				break;
			case UPDATE_ROOM:
				list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				UpdateRoom(list,clientThread);
				break;
			case DELETE_ROOM:
				list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				DeleteRoom(list,clientThread);
				break;
				default:
					break;
		}
	}

	private void DeleteRoom(GenericList<GeneralDomain> list, ClientThreadRequest clientThread) {
		try {
			
			GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new DeleteRoomSO();
			generalSO.runSO(list);
			Controler_Thread.getInstance().sendToClientForDELETE(Type_Of_Data.ROOM, clientThread, TransferClass_Message.SUCCESSFUL_DELETE,list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void UpdateRoom(GenericList<GeneralDomain> list,ClientThreadRequest clientThread) {
		try {
			
			GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new UpdateRoomSO();
			generalSO.runSO(list);
			Controler_Thread.getInstance().sendToClientForUPDATE(Type_Of_Data.ROOM, clientThread, TransferClass_Message.SUCCESSFUL_CHANGE,list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private void enterRoom(GenericList<GeneralDomain> list, ClientThreadRequest clientThread){
		TransferClass transferClass=new TransferClass();
		try {
			
			GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new EnterRoomSO();
			generalSO.runSO(list);
			Controler_Thread.getInstance().sendToClientForADD(Type_Of_Data.ROOM, clientThread, TransferClass_Message.SUCCESSFUL_REGISTRATION,list);
			
		} catch (Exception e) {
			
			transferClass.setMessage(TransferClass_Message.EXCIST_ROOM.getValue());
			clientThread.send(transferClass);
		}
	}
	
	
}
