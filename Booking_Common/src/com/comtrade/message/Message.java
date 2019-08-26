package com.comtrade.message;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.user.User;
import com.comtrade.genericClasses.Generic;
import com.comtrade.genericClasses.GenericList;

public class Message implements Serializable,GeneralDomain{
	
	private User user;
	private String message;
	
	
	public synchronized User getUser() {
		return user;
	}
	public synchronized void setUser(User user) {
		this.user = user;
	}
	public synchronized String getMessage() {
		return message;
	}
	public synchronized void setMessage(String message) {
		this.message = message;
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
