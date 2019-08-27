package com.comtrade.so.rating;

import java.sql.SQLException;

import com.comtrade.cache.Cache;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Property;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.rating.Client_Rating;
import com.comtrade.so.GeneralSystemOperation;

public class EnterRatingSO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		Property property=(Property) object.get(0);
		Client_Rating client=(Client_Rating) object.get(1);
		Cache.getInstance().addInMap(property.getName(),client);

	}

}
