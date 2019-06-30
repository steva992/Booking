package com.comtrade.domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface GeneralDomain {
	public String returnNameOfTable();
	public String returnNameOfColumns();
	public String returnQuestionnaires();
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException;
	public String printIDOfTable();
	public GeneralDomain setResultSetForOne(ResultSet resultSet);

}
