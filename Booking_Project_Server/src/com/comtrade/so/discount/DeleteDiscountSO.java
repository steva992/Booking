package com.comtrade.so.discount;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.discount.Discount;
import com.comtrade.so.GeneralSystemOperation;

public class DeleteDiscountSO extends GeneralSystemOperation<Discount> {

	@Override
	protected void runConcreteSO(Discount object) throws SQLException, Exception {
		IBroker ibroker=new Broker();
		Discount discount=object;
		ibroker.delete(discount);

	}

}
