package com.comtrade.so.user;

import java.sql.SQLException;

import com.comtrade.cache.Cache;
import com.comtrade.domain.user.User;
import com.comtrade.so.GeneralSystemOperation;

public class DeactivatedUserSO extends GeneralSystemOperation<User> {

	@Override
	protected void runConcreteSO(User object) throws SQLException, Exception {
		
		Cache.getInstance().updateInMap(object.getUsername(),object);

	}

}
