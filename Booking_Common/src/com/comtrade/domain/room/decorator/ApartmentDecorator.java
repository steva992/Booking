package com.comtrade.domain.room.decorator;

import com.comtrade.doman.room.IRoom_Info;
import com.comtrade.doman.room.Room_Info;

public class ApartmentDecorator extends Room_Info_Decorator{

	public ApartmentDecorator(IRoom_Info specialRoom) {
		super(specialRoom);
	}

	
	public Room_Info addnewPropertiesForRoom() {
		Room_Info room_info=new Room_Info();
		room_info.setTv(true);
		room_info.setWi_fi(true);
		room_info.setMini_bar(true);
		room_info.setBalkon(true);
		room_info.setLivingRoom(true);
		room_info.setKitchen(true);
		room_info.setSmoking(true);
		return room_info;		
	}
}
