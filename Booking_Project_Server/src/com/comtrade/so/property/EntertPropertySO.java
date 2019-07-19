package com.comtrade.so.property;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Adress;
import com.comtrade.domain.property.GeoLocation;
import com.comtrade.domain.property.Property;
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
		Adress adress=(Adress) object.get(3);
		GeoLocation geoLocation=(GeoLocation) object.get(4);
		Property_Picutre_Album property_picutre_album=(Property_Picutre_Album) object.get(5);
		IBroker iBroker=new Broker();
		
		if(user.getId() == 0) {
			iBroker.enter(user);
			user=(User) iBroker.setID(new User());
			
			user_Additional_Features.setId_User(user.getId());
			iBroker.enter(user_Additional_Features);
			enterProperty(property,adress,iBroker,geoLocation,property_picutre_album,user);
			
		}else {
			enterProperty(property,adress,iBroker,geoLocation,property_picutre_album,user);
		}
		
		
	}

	private void enterProperty(Property property,Adress adress,IBroker iBroker,GeoLocation geoLocation,Property_Picutre_Album property_picutre_album,User user) throws SQLException, Exception {
		property.setId_User(user.getId());
		iBroker.enter(property);
		property=(Property) iBroker.setID(new Property());
		
		adress.setId_property(property.getId());
		iBroker.enter(adress);
		adress=(Adress) iBroker.setID(new Adress());
		
		geoLocation.setId_adress(adress.getId_adress());
		iBroker.enter(geoLocation);
		
		property_picutre_album.setId_property(property.getId());
		for(int i=0;i<5;i++) {
			property_picutre_album.setNumber(i+1);
			iBroker.enter(property_picutre_album);
		}
		
	}

}
