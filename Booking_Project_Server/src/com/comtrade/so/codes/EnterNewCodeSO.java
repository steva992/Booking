package com.comtrade.so.codes;

import java.sql.SQLException;

import com.comtrade.cache.Codes;
import com.comtrade.domain.user.User;
import com.comtrade.so.GeneralSystemOperation;

public class EnterNewCodeSO extends GeneralSystemOperation<User> {

	@Override
	protected void runConcreteSO(User object) throws SQLException, Exception {
		User user=object;
		int number=Codes.getInstance().addCode(user.getId());
		user.setId(number);
	}

}
