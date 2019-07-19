package com.comtrade.so.user;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class EnterUserSO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		User user=(User) object.get(0);
		IBroker iBroker=new Broker();
		iBroker.enter(user);
		User_Info user_Additional_Features=(User_Info) object.get(1);
		user=(User) iBroker.setID(new User());
		user_Additional_Features.setId_User(user.getId());
		iBroker.enter(user_Additional_Features);

	}

}
