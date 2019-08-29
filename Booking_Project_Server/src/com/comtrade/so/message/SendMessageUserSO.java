package com.comtrade.so.message;

import java.sql.SQLException;

import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.controlerBL.Controler_Thread;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class SendMessageUserSO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		GenericList<GeneralDomain>list=object;
		Controler_Thread.getInstance().sendToCertainClient(list,Type_Of_Operation.SENDMESSAGE);
	}

}
