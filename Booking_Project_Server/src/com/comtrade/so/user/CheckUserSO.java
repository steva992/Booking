package com.comtrade.so.user;

import java.sql.SQLException;

import com.comtrade.cache.Cache;
import com.comtrade.domain.user.User;
import com.comtrade.so.GeneralSystemOperation;

public class CheckUserSO extends GeneralSystemOperation<User> {

	@Override
	protected void runConcreteSO(User object) throws SQLException, Exception {
		User user=object;
		boolean excist=Cache.getInstance().checkUserInMap(object);
		if(excist) {
			user.setStatus("excist");
		}
	}

}
