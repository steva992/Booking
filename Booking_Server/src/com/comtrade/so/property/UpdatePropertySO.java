package com.comtrade.so.property;

import java.sql.SQLException;

import com.comtrade.cache.Cache;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Adress;
import com.comtrade.domain.property.GeoLocation;
import com.comtrade.domain.property.Property;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class UpdatePropertySO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		Property property=(Property) object.get(0);
		Adress adress=(Adress) object.get(1);
		GeoLocation geoLocation=(GeoLocation) object.get(2);
		Cache.getInstance().updateInMap(property.getName(),adress);
		Cache.getInstance().updateInMap(property.getName(),geoLocation);
	}

}
