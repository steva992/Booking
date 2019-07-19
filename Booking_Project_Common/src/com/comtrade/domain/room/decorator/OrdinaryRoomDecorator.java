package com.comtrade.domain.room.decorator;

import com.comtrade.doman.room.IRoom_Info;
import com.comtrade.doman.room.Room_Info;

public class OrdinaryRoomDecorator extends Room_Info_Decorator {

	public OrdinaryRoomDecorator(IRoom_Info specialRoom) {
		super(specialRoom);
	}
	
	public Room_Info addnewPropertiesForRoom() {
		Room_Info room_info=new Room_Info();
		room_info.setTv(true);
		room_info.setWi_fi(true);
		room_info.setMini_bar(true);
		return room_info;
		
	}

}
