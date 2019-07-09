package com.comtrade.so.property;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.domain.property.Property_Picutre_Album;
import com.comtrade.so.GeneralSystemOperation;

public class ChangePictureURLPropertySO extends GeneralSystemOperation<Property_Picutre_Album> {

	@Override
	protected void runConcreteSO(Property_Picutre_Album object) throws SQLException, Exception {
		Property_Picutre_Album property_Picture_Album=object;
		Broker broker=new Broker();
		broker.changePictureURL(property_Picture_Album);

	}

}
