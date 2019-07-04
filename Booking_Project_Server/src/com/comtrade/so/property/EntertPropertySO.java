package com.comtrade.so.property;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.Property;
import com.comtrade.domain.Property_Info;
import com.comtrade.domain.User;
import com.comtrade.domain.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class EntertPropertySO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws Exception {
		User user=(User) object.get(0);
		IBroker iBroker=new Broker();
		iBroker.enter(user);
		User_Info user_Additional_Features=(User_Info) object.get(1);
		user=(User) iBroker.setID(new User());
		user_Additional_Features.setId_User(user.getId());
		iBroker.enter(user_Additional_Features);
		Property property=(Property) object.get(2);
		property.setId_User(user.getId());
		iBroker.enter(property);
		property=(Property) iBroker.setID(new Property());
		Property_Info property_info=(Property_Info) object.get(3);
		property_info.setId_property(property.getId());
		iBroker.enter(property_info);
	}

}
