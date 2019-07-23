package com.comtrade.so.user;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.user.User;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class ReturnAdminsSO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		GenericList<GeneralDomain>list=object;
		IBroker ibroker=new Broker();
		list.addAll(ibroker.returnTable(new User()));
		list.addAll(ibroker.returnTable(new Property()));
	}

}
