package com.comtrade.domain.property;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;

public class Property_Picutre_Album implements Serializable,GeneralDomain{
	private int id;
	private String Picutre_URL;
	private int number;
	private int id_property;
	
	public Property_Picutre_Album(int id, String picutre_URL,int number, int id_property) {
		super();
		this.id = id;
		Picutre_URL = picutre_URL;
		this.id_property = id_property;
		this.number=number;
	}
	
	public Property_Picutre_Album() {
		
	}
	
	

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
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

	public int getId_property() {
		return id_property;
	}

	public void setId_property(int id_property) {
		this.id_property = id_property;
	}

	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return "property_picture_album";
	}

	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(PictureURL,id_property,Picture_Number)";
	}

	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return " values(?,?,?)";
	}

	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setString(1,Picutre_URL);
		preparedStatement.setInt(2,id_property);
		preparedStatement.setInt(3,number);
		return preparedStatement;
	}

	@Override
	public String printIDOfTable() {
		// TODO Auto-generated method stub
		return null;
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
	
	
}
