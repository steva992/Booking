package com.comtrade.broker;

import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;

public interface IBroker {

	public void enter(GeneralDomain generalDomain) throws SQLException;
	public GeneralDomain setID(GeneralDomain user) throws SQLException;

}
