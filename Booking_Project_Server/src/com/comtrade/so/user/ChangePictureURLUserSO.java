package com.comtrade.so.user;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.domain.user.User_Info;
import com.comtrade.so.GeneralSystemOperation;

public class ChangePictureURLUserSO extends GeneralSystemOperation<User_Info> {

	@Override
	protected void runConcreteSO(User_Info object) throws SQLException, Exception {
		User_Info user_Info=object;
		Broker broker=new Broker();
		broker.changePictureURLUser(user_Info);
	}

}
