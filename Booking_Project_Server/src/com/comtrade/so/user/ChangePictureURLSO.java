package com.comtrade.so.user;

import java.sql.SQLException;

import com.comtrade.cache.Cache;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class ChangePictureURLSO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		User user=(User) object.get(0);
		User_Info user_info=(User_Info) object.get(1);
		Cache.getInstance().updateInMap(user.getUsername(),user_info);
	}

}
