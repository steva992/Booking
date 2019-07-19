package com.comtrade.so.discount;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.discount.Discount;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class EnterDiscountSO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		IBroker ibroker=new Broker();
		GenericList<GeneralDomain>list=object;
		Discount discount=(Discount) object.get(0);
		ibroker.enter(discount);
		discount=(Discount) ibroker.setID(new Discount());
		list.add(discount);
	}

}
