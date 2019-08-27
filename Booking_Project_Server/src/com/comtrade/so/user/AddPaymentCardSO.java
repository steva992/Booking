package com.comtrade.so.user;

import java.sql.SQLException;

import com.comtrade.cache.Cache;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.user.PaymentUserCard;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class AddPaymentCardSO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		User user=(User) object.get(0);
		PaymentUserCard payment=(PaymentUserCard) object.get(1);
		Cache.getInstance().addInMap(user.getUsername(),payment);
	}

}
