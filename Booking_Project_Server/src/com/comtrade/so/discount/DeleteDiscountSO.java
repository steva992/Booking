package com.comtrade.so.discount;

import java.sql.SQLException;

import com.comtrade.cache.Cache;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.Type_Of_Data;
import com.comtrade.controlerBL.Controler_Thread;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.discount.Discount;
import com.comtrade.domain.property.Property;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class DeleteDiscountSO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		Property property=(Property) object.get(0);
		Discount discount1=(Discount) object.get(1);
		Cache.getInstance().deleteFromMap(property.getName(),discount1);
	}

}
