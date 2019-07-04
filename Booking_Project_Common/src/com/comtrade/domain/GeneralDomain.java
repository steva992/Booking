package com.comtrade.domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.genericClasses.Generic;

public interface GeneralDomain extends Generic{
	public String returnNameOfTable();
	public String returnNameOfColumns();
	public String returnQuestionnaires();
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException;
	public String printIDOfTable();
	public GeneralDomain setResultSetForOne(ResultSet resultSet);
	public PreparedStatement setPSforID(PreparedStatement preparedStatement);
	

}
