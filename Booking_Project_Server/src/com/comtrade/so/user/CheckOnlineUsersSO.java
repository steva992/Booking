package com.comtrade.so.user;

import java.sql.SQLException;

import com.comtrade.cache.Cache;
import com.comtrade.controlerBL.Controler_Thread;
import com.comtrade.domain.user.User;
import com.comtrade.so.GeneralSystemOperation;

public class CheckOnlineUsersSO extends GeneralSystemOperation<User> {

	@Override
	protected void runConcreteSO(User object) throws SQLException, Exception {
		User user=object;
		int number=Controler_Thread.getInstance().checkOnlineUser(user);
		user.setId(number);
	}

}
