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
	private int property_code;
	private int adress_code;

	
	
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







	public int getProperty_code() {
		return property_code;
	}







	public void setProperty_code(int property_code) {
		this.property_code = property_code;
	}







	public int getAdress_code() {
		return adress_code;
	}







	public void setAdress_code(int adress_code) {
		this.adress_code = adress_code;
	}







	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return "adress";
	}


	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(City,Street,Number,Country,property_code,adress_code)";
	}


	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return " values (?,?,?,?,?,?)";
	}


	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setString(1,City);
		preparedStatement.setString(2,Street);
		preparedStatement.setInt(3,houseNumber);
		preparedStatement.setString(4,Country);
		preparedStatement.setInt(5,property_code);
		preparedStatement.setInt(6,adress_code);
		return preparedStatement;
	}


	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return "property_code";
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
				adress.setProperty_code(resultSet.getInt("property_code"));
				adress.setAdress_code(resultSet.getInt("adress_code"));
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
			preparedStatement.setInt(1,adress_code);
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
		stringBuffer.append("Country=? ,");
		stringBuffer.append("adress_code=? ");
		return stringBuffer.toString();
	}


	@Override
	public String printUniqueValueOfTable() {
		// TODO Auto-generated method stub
		return "adress_code";
	}







	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + adress_code;
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
		Adress other = (Adress) obj;
		if (adress_code != other.adress_code)
			return false;
		return true;
	}


	
	

	
	
	
}
