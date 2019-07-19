package com.comtrade.so.room;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.doman.room.Room;
import com.comtrade.doman.room.Room_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class UpdateRoomSO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		GenericList<GeneralDomain>listRooms=object;
		Room room=(Room) listRooms.get(0);
		Room_Info room_info=(Room_Info) listRooms.get(1);
		IBroker iBroker=new Broker();
		iBroker.updateWithMore(room,room.getId());
		iBroker.update(room_info);

	}

}
