package com.comtrade.so.message;

import java.sql.SQLException;

import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.controlerBL.Controler_Thread;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.user.User;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class DeleteFromChatSO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		
		Controler_Thread.getInstance().sendToCertainClient(object,Type_Of_Operation.DELETE_FROM_ADMIN_CHAT);

	}

}
