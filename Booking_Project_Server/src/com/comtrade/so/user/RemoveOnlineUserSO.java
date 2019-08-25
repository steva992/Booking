package com.comtrade.so.user;

import java.sql.SQLException;

import com.comtrade.controlerPL.Controler_Thread;
import com.comtrade.domain.user.User;
import com.comtrade.so.GeneralSystemOperation;

public class RemoveOnlineUserSO extends GeneralSystemOperation<User> {

	@Override
	protected void runConcreteSO(User object) throws SQLException, Exception {
		User user=object;
		Controler_Thread.getInstance().removeThreadMap(user.getUsername());
		
	}

}
