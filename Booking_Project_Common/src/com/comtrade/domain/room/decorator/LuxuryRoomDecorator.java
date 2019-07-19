package com.comtrade.domain.room.decorator;

import com.comtrade.doman.room.IRoom_Info;
import com.comtrade.doman.room.Room_Info;

public class LuxuryRoomDecorator extends Room_Info_Decorator{
	
	public LuxuryRoomDecorator(IRoom_Info specialRoom) {
		super(specialRoom);
	}
	
	public Room_Info addnewPropertiesForRoom() {
		Room_Info room_info= specialRoom.addnewPropertiesForRoom();
		room_info.setTv(true);
		room_info.setWi_fi(true);
		room_info.setMini_bar(true);
		room_info.setJacuzzi(true);
		room_info.setSmoking(true);
		room_info.setAir_condition(true);
		room_info.setUnderFloorHeating(true);
		return room_info;
	}

}
