package com.comtrade.domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Property_Info extends Property implements GeneralDomain,Serializable{
	private int id;
	private int id_property;
	private String adress_street;
	private String city;
	private String state;
	private String coordinates;
	private int adress_number;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_property() {
		return id_property;
	}
	public void setId_property(int id_property) {
		this.id_property = id_property;
	}
	public String getAdress_street() {
		return adress_street;
	}
	public void setAdress_street(String adress_street) {
		this.adress_street = adress_street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	public int getAdress_number() {
		return adress_number;
	}
	public void setAdress_number(int adress_number) {
		this.adress_number = adress_number;
	}
	
	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return "property_info";
	}
	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(Id_Property,Adress_Street,City,State,Coordinates,Adress_Number)";
	}
	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return " values(?,?,?,?,?,?)";
	}
	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setInt(1,id_property);
		preparedStatement.setString(2,adress_street);
		preparedStatement.setString(3,city);
		preparedStatement.setString(4,state);
		preparedStatement.setString(5,coordinates);
		preparedStatement.setInt(6,adress_number);
		return preparedStatement;
	}
	
}
