package com.comtrade.so.user;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.user.PaymentUserCard;
import com.comtrade.so.GeneralSystemOperation;

public class AddNewCardSO extends GeneralSystemOperation<PaymentUserCard> {

	@Override
	protected void runConcreteSO(PaymentUserCard object) throws SQLException, Exception {
		PaymentUserCard payment=object;
		IBroker ibroker=new Broker();
		ibroker.enter(payment);

	}

}
