package com.comtrade.so.user;

import java.sql.SQLException;

import com.comtrade.cache.Cache;
import com.comtrade.controlerPL.Controler_Thread;
import com.comtrade.domain.user.User;
import com.comtrade.so.GeneralSystemOperation;

public class LogInSO extends GeneralSystemOperation<User> {

	@Override
	protected void runConcreteSO(User object) throws SQLException, Exception {
		User user=object;
		User login=Cache.getInstance().checkLoginUserInMap(user);
		if(login != null) {
			user.setStatus(login.getStatus());
		}
	}

}
