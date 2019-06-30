package com.comtrade.so.user;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.domain.User_Additional_Features;
import com.comtrade.genericClasses.GenericClass;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class EnterUserAndAdditionalUser extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
		protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException {
			User user=(User) object.get(0);
			User_Additional_Features user_Additional_Features=(User_Additional_Features) object.get(1);
			
			IBroker iBroker=new Broker();
			iBroker.enter(user);
			
			user=(User) iBroker.setID(new User());
			user_Additional_Features.setId(user.getId());
			iBroker.enter(user_Additional_Features);
		}
		
	}
		