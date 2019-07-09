package com.comtrade.so.property;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.property.Property_Info;
import com.comtrade.domain.property.Property_Picutre_Album;
import com.comtrade.domain.user.User;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class BackAllForProperty extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		GenericList<GeneralDomain>list=object;
		Broker broker=new Broker();
		User user=(User) object.get(0);
		
		Property property=new Property();
		property=(Property) broker.returnInfo(user,property);
		
		Property_Info property_info=new Property_Info();
		property_info=(Property_Info) broker.returnInfo(property, property_info);
				
		GenericList<GeneralDomain>listAlbum=new GenericList<GeneralDomain>();
		listAlbum=broker.returnAlbumOfPicture(property);
		
		list.add(property);
		list.add(property_info);
		for(int i=0;i<listAlbum.size();i++) {
			list.add(listAlbum.get(i));
		}
	}

}
