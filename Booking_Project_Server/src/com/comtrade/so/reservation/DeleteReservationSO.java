package com.comtrade.so.reservation;

import java.sql.SQLException;

import com.comtrade.cache.Cache;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Property;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.reservation.Reservation;
import com.comtrade.so.GeneralSystemOperation;

public class DeleteReservationSO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		Property property=(Property) object.get(0);
		Reservation reservation=(Reservation) object.get(1);
		Cache.getInstance().deleteFromMap(property.getName(),reservation);

	}

}
