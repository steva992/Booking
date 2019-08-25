package com.comtrade.so.user;

import java.sql.SQLException;

import com.comtrade.cache.Cache;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.user.User;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class ChangePasswordSO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		User user=(User) object.get(0);
		User user1=(User) object.get(1);
		boolean correct=Cache.getInstance().checkUserPassword(user);
		if(correct) {
			Cache.getInstance().updateUserPassword(user1);
			user1.setStatus("true");
		}
	}

}
