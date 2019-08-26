package com.comtrade.controlerClient;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Property;
import com.comtrade.doman.room.Room;
import com.comtrade.doman.room.Room_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.panel.admin.Admin_Panel;
import com.comtrade.panel.user.User_Panel;
import com.comtrade.transfer.TransferClass;

public class ControlerRoom {
	private static volatile ControlerRoom instance;
	private volatile Property property;
	private volatile Room room;
	private volatile Room_Info room_info;
	private volatile String message;
	private User_Panel userPanel;
	private volatile int number=0;
	
	

	public synchronized int getNumber() {
		return number;
	}

	public synchronized void setNumber(int number) {
		this.number = number;
	}

	public synchronized void setUserPanel(User_Panel userPanel) {
		this.userPanel = userPanel;
	}

	public synchronized String getMessage() {
		while(number==0) {
			getNumber();
		}
		return message;
	}

	public synchronized Property getProperty() {
		while(number==0) {
			getNumber();
		}
		return property;
	}

	public synchronized Room getRoom() {
		while(number==0) {
			getNumber();
		}
		return room;
	}

	public synchronized Room_Info getRoom_info() {
		while(number==0) {
			getNumber();
		}
		return room_info;
	}

	private ControlerRoom() {
	
	}

		public static ControlerRoom getInstance() {
			if(instance==null) {
				synchronized (ControlerRoom.class) {
					if(instance==null) {
						instance=new ControlerRoom();
					}
				}
			}
		return instance;
		}

	public void CheckTheOperation(TransferClass transferClass) {
		TransferClass transferClass2=new TransferClass();
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		switch (transferClass.getType_Of_operation()) {
		
		case ADD:
			list=(GenericList<GeneralDomain>) transferClass.getServer_Object_Response();
			property=(Property) list.get(0);
			room=(Room) list.get(1);
			room_info=(Room_Info) list.get(2);
			message=transferClass.getMessage();
			if(userPanel != null) {
				userPanel.addRoom(property.getName(),room,room_info);
			}
			this.number=1;
			break;
		case UPDATE:
			list=(GenericList<GeneralDomain>) transferClass.getServer_Object_Response();
			property=(Property) list.get(0);
			room=(Room) list.get(1);
			room_info=(Room_Info) list.get(2);
			message=transferClass.getMessage();
			if(userPanel != null) {
				userPanel.updateRoom(property.getName(),room);
			}
			this.number=1;
			break;	
		case DELETE:
			list=(GenericList<GeneralDomain>) transferClass.getServer_Object_Response();
			property=(Property) list.get(0);
			room=(Room) list.get(1);
			room_info=(Room_Info) list.get(2);
			message=transferClass.getMessage();
			if(userPanel != null) {
				userPanel.deleteRoom(property.getName(),room,room_info);
			}
			this.number=1;
			break;	
		}	
}

	

}	
