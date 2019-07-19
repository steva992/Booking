package com.comtrade.so.room;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.doman.room.Room;
import com.comtrade.doman.room.Room_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class EntertRoomSO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		GenericList<GeneralDomain>list=object;
		Room room =(Room) list.get(0);
		
		IBroker iBroker=new Broker();
		iBroker.enter(room);
		
		Room_Info room_Info=new Room_Info();
		room_Info=(Room_Info) list.get(1);
		
		room=(Room) iBroker.setID(new Room());
		
		room_Info.setId_room(room.getId());
		iBroker.enter(room_Info);	
		list.add(room);
	}

}
