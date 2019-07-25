package com.comtrade.so.reservation;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.reservation.Reservation;
import com.comtrade.so.GeneralSystemOperation;

public class AddNewReservationSO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		IBroker ibroker=new Broker();
		GenericList<GeneralDomain>list=object;
		Reservation reservation=(Reservation) list.get(0);
		ibroker.enter(reservation);
		list.add(reservation);

	}

}
