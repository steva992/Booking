package com.comtrade.domain.property;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.genericClasses.GenericList;

public class Adress implements GeneralDomain,Serializable{
	private int id_adress;
	private String Country;
	private String Street;
	private String City;
	private int houseNumber;
	private int id_property;
	
	public Adress(int id_adress, String country, String street, String city, int houseNumber, int id_geoLocation) {
		super();
		this.id_adress = id_adress;
		Country = country;
		Street = street;
		City = city;
		this.houseNumber = houseNumber;
		this.id_property = id_geoLocation;
	}
	
	
	public Adress() {
		
	}


	

	public int getId_adress() {
		return id_adress;
	}


	public void setId_adress(int id_adress) {
		this.id_adress = id_adress;
	}


	public String getCountry() {
		return Country;
	}


	public void setCountry(String country) {
		Country = country;
	}


	public String getStreet() {
		return Street;
	}


	public void setStreet(String street) {
		Street = street;
	}


	public String getCity() {
		return City;
	}


	public void setCity(String city) {
		City = city;
	}


	public int getHouseNumber() {
		return houseNumber;
	}


	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
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
		return "adress";
	}


	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(City,Street,Number,Country,id_property)";
	}


	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return " values (?,?,?,?,?)";
	}


	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setString(1,City);
		preparedStatement.setString(2,Street);
		preparedStatement.setInt(3,houseNumber);
		preparedStatement.setString(4,Country);
		preparedStatement.setInt(5,id_property);
		return preparedStatement;
	}


	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return "id_property";
	}


	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) {
		Adress adress=new Adress();
		try {
			if(resultSet.next()) {
				adress.setId_adress(resultSet.getInt("id_Adress"));
				adress.setCity(resultSet.getString("City"));
				adress.setStreet(resultSet.getString("Street"));
				adress.setHouseNumber(resultSet.getInt("Number"));
				adress.setCountry(resultSet.getString("Country"));
				adress.setId_property(resultSet.getInt("id_property"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adress;
	}


	@Override
	public PreparedStatement setPSforID(PreparedStatement preparedStatement){
		try {
			preparedStatement.setInt(1,id_adress);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preparedStatement;
	}


	@Override
	public GenericList<GeneralDomain> setResultSetForMore(ResultSet resultSet) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String setColumnForUpdate() {
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("City=? ,");
		stringBuffer.append("Street=? ,");
		stringBuffer.append("Number=? ,");
		stringBuffer.append("Country=? ");
		return stringBuffer.toString();
	}


	@Override
	public String printIDOfTable() {
		// TODO Auto-generated method stub
		return "id_Adress";
	}


	


	
	
	
}
