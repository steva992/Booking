package com.comtrade.so.user;

import java.sql.SQLException;

import com.comtrade.cache.Cache;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class EnterUserSO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		GenericList<GeneralDomain>list=object;
		User user=(User) list.get(0);
		User userExcist=new User();
		boolean excist=Cache.getInstance().checkUserInMap(user);
		if(excist) {
			userExcist.setStatus("excist");
			list.add(userExcist);
		}else {
			Cache.getInstance().addListInMap(user.getUsername(),object);
		}
	}

}
