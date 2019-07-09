package com.comtrade.so.user;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.user.User;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class UserLogInSO extends GeneralSystemOperation<GenericList<User>> {

	@Override
	protected void runConcreteSO(GenericList<User> object) throws SQLException {
		GenericList<User>lista=object;
		User user=lista.get(0);
		Broker broker=new Broker();
		user=broker.login(user);
		lista.add(user);
	}

}
