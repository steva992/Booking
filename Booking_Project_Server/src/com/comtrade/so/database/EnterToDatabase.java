package com.comtrade.so.database;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.TypeForDatabase;
import com.comtrade.domain.discount.Discount;
import com.comtrade.domain.property.Adress;
import com.comtrade.domain.property.GeoLocation;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.property.Property_Picutre_Album;
import com.comtrade.domain.user.PaymentUserCard;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.doman.room.Room;
import com.comtrade.doman.room.Room_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.rating.Client_Rating;
import com.comtrade.reservation.Reservation;
import com.comtrade.so.GeneralSystemOperation;

public class EnterToDatabase extends GeneralSystemOperation<GenericList<TypeForDatabase>> {

	@Override
	protected void runConcreteSO(GenericList<TypeForDatabase> object) throws SQLException, Exception {
		GenericList<TypeForDatabase>list=object;
		IBroker broker=new Broker();
		broker.execute(list);
		
	}

}
