package com.comtrade.so.property;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Adress;
import com.comtrade.domain.property.GeoLocation;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class UpdatePropertySO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		Adress adress=(Adress) object.get(0);
		GeoLocation geoLocation=(GeoLocation) object.get(1);
		IBroker ibroker=new Broker();
		ibroker.update(adress);
		ibroker.update(geoLocation);
	}

}
