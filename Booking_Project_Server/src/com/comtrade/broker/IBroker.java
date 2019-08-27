package com.comtrade.broker;

import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.TypeForDatabase;
import com.comtrade.domain.discount.Discount;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.doman.room.Room;
import com.comtrade.genericClasses.GenericList;

public interface IBroker {

	public GeneralDomain returnInfo(GeneralDomain generalDomain, GeneralDomain generalDoman_Info) throws SQLException;
	
	public GeneralDomain returnParrentInfo(int id, GeneralDomain generalDoman_Info) throws SQLException;

	public GenericList<GeneralDomain> returnInfoForMore(GeneralDomain generalDomain, GeneralDomain generalDomain_Info)throws SQLException;

	public GenericList<GeneralDomain> returnParrentInfoForMore(int id, GeneralDomain generalDomain_Info) throws SQLException;
	
	public GenericList<GeneralDomain>  returnTable(GeneralDomain generalDomain) throws SQLException;

	public void execute(GenericList<TypeForDatabase> list) throws SQLException;

}
