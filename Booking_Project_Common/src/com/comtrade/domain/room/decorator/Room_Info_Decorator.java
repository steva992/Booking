package com.comtrade.domain.room.decorator;

import com.comtrade.doman.room.IRoom_Info;
import com.comtrade.doman.room.Room_Info;

public abstract class Room_Info_Decorator implements IRoom_Info{
	protected IRoom_Info specialRoom;

	public Room_Info_Decorator(IRoom_Info specialRoom) {
		super();
		this.specialRoom = specialRoom;
	}
	
	
	public Room_Info addnewPropertiesForRoom() {
		return specialRoom.addnewPropertiesForRoom();
		
	}
}
