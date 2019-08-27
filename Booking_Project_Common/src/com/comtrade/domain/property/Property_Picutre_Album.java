package com.comtrade.domain.property;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.genericClasses.GenericList;

public class Property_Picutre_Album implements Serializable,GeneralDomain{
	private int id;
	private String Picutre_URL;
	private int number;
	private int property_code;
	
	public Property_Picutre_Album(int id, String picutre_URL,int number, int property_code) {
		super();
		this.id = id;
		Picutre_URL = picutre_URL;
		this.property_code = property_code;
		this.number=number;
	}
	
	public Property_Picutre_Album() {
		
	}
	
	

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPicutre_URL() {
		return Picutre_URL;
	}

	public void setPicutre_URL(String picutre_URL) {
		Picutre_URL = picutre_URL;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getProperty_code() {
		return property_code;
	}

	public void setProperty_code(int property_code) {
		this.property_code = property_code;
	}

	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return "property_picture_album";
	}

	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(PictureURL,Picture_Number,property_code)";
	}

	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return " values(?,?,?)";
	}

	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setString(1,Picutre_URL);
		preparedStatement.setInt(2,number);
		preparedStatement.setInt(3,property_code);
		return preparedStatement;
	}

	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return "Picture_number=? and property_code";
	}

	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreparedStatement setPSforID(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericList<GeneralDomain> setResultSetForMore(ResultSet resultSet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setColumnForUpdate() {
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("PictureURL=? ");
		return stringBuffer.toString();
	}

	@Override
	public String printUniqueValueOfTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		result = prime * result + property_code;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Property_Picutre_Album other = (Property_Picutre_Album) obj;
		if (number != other.number)
			return false;
		if (property_code != other.property_code)
			return false;
		return true;
	}

	
	
	
}
