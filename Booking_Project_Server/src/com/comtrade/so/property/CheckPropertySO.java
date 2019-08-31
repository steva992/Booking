package com.comtrade.so.property;

import java.sql.SQLException;

import com.comtrade.cache.Cache;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.user.PaymentUserCard;
import com.comtrade.domain.user.User;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class CheckPropertySO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		GenericList<GeneralDomain>list=object;
		Property property=new Property();
		if(list.get(0) instanceof User) {
			property=(Property) list.get(2);
		}else {
			property=(Property) list.get(0);
		}
		boolean excist=Cache.getInstance().checkPropertyInMap(property);
		
		if(excist) {
			list.add(new PaymentUserCard());
		}

	}

}
