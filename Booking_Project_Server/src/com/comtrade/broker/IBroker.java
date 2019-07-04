package com.comtrade.broker;

import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.domain.User_Info;

public interface IBroker {

	public void enter(GeneralDomain generalDomain) throws SQLException, Exception;

	public GeneralDomain setID(GeneralDomain user) throws SQLException, Exception;

	public GeneralDomain returnInfo(GeneralDomain generalDomain, GeneralDomain generalDoman_Info) throws SQLException;

}
