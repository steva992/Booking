package com.comtrade.so.user;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.user.User;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class CheckUserSO extends GeneralSystemOperation<GenericList<User>> {

	@Override
	protected void runConcreteSO(GenericList<User> object) throws SQLException {
		GenericList<User>userList=object;
		User user=userList.get(0);
		Broker broker=new Broker();
		userList.add(broker.checkUser(user.getUsername()));
	}

}
