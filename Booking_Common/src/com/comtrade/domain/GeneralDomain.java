package com.comtrade.domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.genericClasses.Generic;
import com.comtrade.genericClasses.GenericList;

public interface GeneralDomain extends Generic{
	
	
	public String returnNameOfTable();
	
	public String returnNameOfColumns();
	
	public String returnQuestionnaires();
	
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException;
	
	public String printIDOfParrentTable();
	
	public GeneralDomain setResultSetForOne(ResultSet resultSet) throws SQLException;
	
	public GenericList<GeneralDomain> setResultSetForMore(ResultSet resultSet) throws SQLException;
	
	public PreparedStatement setPSforID(PreparedStatement preparedStatement) throws SQLException;
	
	public String setColumnForUpdate();
	
	public String printUniqueValueOfTable();
	

}
