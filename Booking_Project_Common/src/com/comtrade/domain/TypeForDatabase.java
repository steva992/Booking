package com.comtrade.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.genericClasses.GenericList;

public class TypeForDatabase implements GeneralDomain{
	
	private int type;
	private GeneralDomain generalDomain;
	
	public TypeForDatabase() {
		
	}
	
	public synchronized int getType() {
		return type;
	}
	public synchronized void setType(int type) {
		this.type = type;
	}
	public synchronized GeneralDomain getGeneralDomain() {
		return generalDomain;
	}
	public synchronized void setGeneralDomain(GeneralDomain generalDomain) {
		this.generalDomain = generalDomain;
	}

	
	
	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericList<GeneralDomain> setResultSetForMore(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreparedStatement setPSforID(PreparedStatement preparedStatement) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setColumnForUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String printUniqueValueOfTable() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
