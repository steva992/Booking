package com.comtrade.so.property;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.property.Property_Info;
import com.comtrade.domain.property.Property_Picutre_Album;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class EntertPropertySO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws Exception {
		User user=(User) object.get(0);
		User_Info user_Additional_Features=(User_Info) object.get(1);
		Property property=(Property) object.get(2);
		Property_Info property_info=(Property_Info) object.get(3);
		Property_Picutre_Album property_picutre_album=(Property_Picutre_Album) object.get(4);
		
		
		IBroker iBroker=new Broker();
		iBroker.enter(user);
		user=(User) iBroker.setID(new User());
		user_Additional_Features.setId_User(user.getId());
		iBroker.enter(user_Additional_Features);
		property.setId_User(user.getId());
		iBroker.enter(property);
		property=(Property) iBroker.setID(new Property());
		
		property_info.setId_property(property.getId());
		iBroker.enter(property_info);
		property_picutre_album.setId_property(property.getId());
		for(int i=0;i<5;i++) {
			property_picutre_album.setNumber(i+1);
			iBroker.enter(property_picutre_album);
		}
	}

}
