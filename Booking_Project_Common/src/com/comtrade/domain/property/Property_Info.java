package com.comtrade.domain.property;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;

public class Property_Info implements GeneralDomain,Serializable{
	private int id;
	private int id_property;
	private String adress_street;
	private String city;
	private String Country;
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
	public String getCountry() {
		return Country;
	}
	public void setCountry(String state) {
		this.Country = state;
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
		return "(Id_Property,Adress_Street,City,Country,Coordinates,Adress_Number)";
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
		preparedStatement.setString(4,Country);
		preparedStatement.setString(5,coordinates);
		preparedStatement.setInt(6,adress_number);
		return preparedStatement;
	}
	@Override
	public String printIDOfTable() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) {
		Property_Info property_Info=new Property_Info();
		try {
			if(resultSet.next()) {
				property_Info.setId(resultSet.getInt("Id_Property_Info"));
				property_Info.setId_property(resultSet.getInt("Id_Property"));
				property_Info.setAdress_street(resultSet.getString("Adress_Street"));
				property_Info.setCity(resultSet.getString("City"));
				property_Info.setCountry(resultSet.getString("Country"));
				property_Info.setCoordinates(resultSet.getString("Coordinates"));
				property_Info.setAdress_number(resultSet.getInt("Adress_Number"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return property_Info;
	}
	@Override
	public PreparedStatement setPSforID(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
