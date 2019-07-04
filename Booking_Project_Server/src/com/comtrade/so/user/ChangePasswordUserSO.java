package com.comtrade.so.user;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.domain.User;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class ChangePasswordUserSO extends GeneralSystemOperation<GenericList<User>> {

	@Override
	protected void runConcreteSO(GenericList<User> object) throws SQLException {
		GenericList<User>listUser=object;
		User userCheck=listUser.get(0);
		User userChange=listUser.get(1);
		Broker broker=new Broker();
        userCheck=broker.checkUser(userCheck.getUsername(),userCheck.getPassword());
        if(userCheck.getId() != 0) {
        	 broker.changePassword(userChange);
        }
        listUser.add(userCheck);
	}

}
