package com.comtrade.so.room;

import java.sql.SQLException;

import com.comtrade.cache.Cache;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Property;
import com.comtrade.doman.room.Room;
import com.comtrade.doman.room.Room_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class UpdateRoomSO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		
		Property property=(Property) object.get(0);
		Room room=(Room) object.get(1);
		Room_Info room_info=(Room_Info) object.get(2);
		Cache.getInstance().updateInMap(property.getName(),room);
		Cache.getInstance().updateInMap(property.getName(),room_info);
	}

}
